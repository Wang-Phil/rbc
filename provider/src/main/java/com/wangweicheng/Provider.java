package com.wangweicheng;
/*
 *@version 1.0
 *@Author: WangWeicheng
 *@Date: 2024/6/14
 *@Time: 21:00
 */

import com.wangweicheng.common.URL;
import com.wangweicheng.protocal.HttpServer;
import com.wangweicheng.register.LocalRegister;
import com.wangweicheng.register.MapRemoteRegister;

public class Provider {
    public static void main(String[] args) {
        //本地注册服务
        LocalRegister.register(HelloService.class.getName(), "1.0" ,HelloServiceImpl.class);
        LocalRegister.register(HelloService.class.getName(), "2.0" ,HelloServiceImpl2.class);

        //注册中心注册，存储对应的端口号和ip地址
        URL url = new URL("localhost",8080);
        MapRemoteRegister.register(HelloService.class.getName(),url);

        //支持网络请求netty，tomcat，socket
        //扩展框架，可以支持配置
        HttpServer server = new HttpServer();
        server.start(url.getHostname(), url.getPort());
    }
}
