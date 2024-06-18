package com.wangweicheng;
/*
 *@version 1.0
 *@Author: WangWeicheng
 *@Date: 2024/6/14
 *@Time: 21:00
 */

import com.wangweicheng.protocal.HttpServer;
import com.wangweicheng.register.LocalRegister;

public class Provider {
    public static void main(String[] args) {
        LocalRegister.register(HelloService.class.getName(), "1.0" ,HelloServiceImpl.class);
        LocalRegister.register(HelloService.class.getName(), "2.0" ,HelloServiceImpl2.class);
        //支持网络请求netty，tomcat，socket
        //扩展框架，可以支持配置
        HttpServer server = new HttpServer();
        server.start("localhost", 8080);
    }
}
