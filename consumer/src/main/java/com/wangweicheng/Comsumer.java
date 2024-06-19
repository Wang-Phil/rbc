package com.wangweicheng;
/*
 *@version 1.0
 *@Author: WangWeicheng
 *@Date: 2024/6/13
 *@Time: 17:07
 */

import com.wangweicheng.common.Invocation;
import com.wangweicheng.protocal.HttpClient;

public class Comsumer {
    public static void main(String[] args) {
        Invocation invocation =  new Invocation(HelloService.class.getName(),
        "sayHello", new Class[]{String.class}, new Object[]{"wangweicheng"});

        HttpClient httpClient = new HttpClient();
        String result = httpClient.send("localhost", 8080, invocation);
        System.out.println(result);
    }
}
