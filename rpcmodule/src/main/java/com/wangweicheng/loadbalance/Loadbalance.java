package com.wangweicheng.loadbalance;

import com.wangweicheng.common.URL;

import java.util.List;
import java.util.Random;

/*
 *@version 1.0
 *@Author: WangWeicheng
 *@Date: 2024/6/19
 *@Time: 22:53
 */
//提供负载均衡的策略，让comsumer可以选择对应的服务器地址
public class Loadbalance {
    public  static URL random(List<URL> urls) {
        Random rand = new Random();
        int index = rand.nextInt(urls.size());
        return urls.get(index);
    }
}
