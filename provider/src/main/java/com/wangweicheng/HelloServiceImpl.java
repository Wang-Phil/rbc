package com.wangweicheng;
/*
 *@version 1.0
 *@Author: WangWeicheng
 *@Date: 2024/6/13
 *@Time: 17:06
 */

public class HelloServiceImpl implements HelloService {
    public String sayHello(String name) {
        return "hello：" + name;
    }
}
