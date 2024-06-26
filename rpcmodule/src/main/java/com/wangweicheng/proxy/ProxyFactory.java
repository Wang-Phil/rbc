package com.wangweicheng.proxy;
/*
 *@version 1.0
 *@Author: WangWeicheng
 *@Date: 2024/6/19
 *@Time: 22:21
 */

import com.wangweicheng.common.Invocation;
import com.wangweicheng.common.URL;
import com.wangweicheng.loadbalance.Loadbalance;
import com.wangweicheng.protocal.HttpClient;
import com.wangweicheng.register.MapRemoteRegister;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

public class ProxyFactory {
    //产生代理对象
    public static <T> T getProxy(Class interfaceClass) {
        //读取用户配置,获取到代理对象
        Object proxyInstance = Proxy.newProxyInstance(interfaceClass.getClassLoader(), new Class[]{interfaceClass}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

                //服务Mock
                String mock = System.getProperty("mock");
                if (mock != null && mock.startsWith("return:")) {
                    String result = mock.replace("return:", "");
                    return result;
                }

                //invocation对象从原来的自己创建到现在可以用invoke对象里面的参数来进行实例化invocation对象
                Invocation invocation =  new Invocation(interfaceClass.getName(),
                        method.getName(), method.getParameterTypes(), args);
                HttpClient httpClient = new HttpClient();

                //服务发现
                List<URL> urls = MapRemoteRegister.get(interfaceClass.getName());

                //服务调用
                String result = null;
                List<URL> invokeUrls = new ArrayList<URL>();        //保存已经调用过的服务
                //实现报错更友好

                //服务重试
                int max = 2;
                while(max > 0){

                    //负载均衡
                    urls.remove(invokeUrls);        //排出当前的已经调用过的服务
                    URL url = Loadbalance.random(urls);
                    invokeUrls.add(url);            //将当前服务加入到list中

                    try{

                        //localhost还需要优化，让其可以配置
                        result = httpClient.send(url.getHostname(), url.getPort(), invocation);
                        //invoke返回值就是代理对象执行的返回值
                    }catch (Exception e){
                        //服务重试
                        if(max-- > 0) continue;
                        //给配置项，error-callback=com.wangweicheng.HelloServiceErrorCallback
                        //容错逻辑
                        return "报错了";
                    }
                }


                return result;
            }
        });
        return (T) proxyInstance;
    }
}
