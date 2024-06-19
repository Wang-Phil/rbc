package com.wangweicheng;
/*
 *@version 1.0
 *@Author: WangWeicheng
 *@Date: 2024/6/13
 *@Time: 17:07
 */

import com.wangweicheng.common.Invocation;
import com.wangweicheng.protocal.HttpClient;
import com.wangweicheng.proxy.ProxyFactory;

public class Comsumer {
    public static void main(String[] args) {
        //得到代理对象
        HelloService helloService = ProxyFactory.getProxy(HelloService.class);
        //代理对象执行sayhello方法
        String result = helloService.sayHello("wangweicheng");
        System.out.println(result);
    }
}
