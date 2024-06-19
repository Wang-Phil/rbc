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
import java.util.List;

public class ProxyFactory {
    //产生代理对象
    public static <T> T getProxy(Class interfaceClass) {
        //读取用户配置,获取到代理对象
        Object proxyInstance = Proxy.newProxyInstance(interfaceClass.getClassLoader(), new Class[]{interfaceClass}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                //invocation对象从原来的自己创建到现在可以用invoke对象里面的参数来进行实例化invocation对象
                Invocation invocation =  new Invocation(interfaceClass.getName(),
                        method.getName(), method.getParameterTypes(), args);
                HttpClient httpClient = new HttpClient();

                //服务发现
                List<URL> urls = MapRemoteRegister.get(interfaceClass.getName());

                //负载均衡
                URL url = Loadbalance.random(urls);

                //服务调用
                //localhost还需要优化，让其可以配置
                String result = httpClient.send(url.getHostname(), url.getPort(), invocation);
                //invoke返回值就是代理对象执行的返回值
                return result;
            }
        });
        return (T) proxyInstance;
    }
}
