package com.wangweicheng.register;

import com.wangweicheng.common.URL;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 *@version 1.0
 *@Author: WangWeicheng
 *@Date: 2024/6/19
 *@Time: 22:45
 */
//模拟远程注册中心,provider可能有多个节点，作为集群，提供服务，所以provider采用list方法
//Map结构 String, List
public class MapRemoteRegister {
    private static Map<String, List<URL>> map = new HashMap<>();
    public static void register(String interfaceName, URL url){
        List<URL> list = map.get(interfaceName);
        if(list == null){
            list = new ArrayList<>();
        }
        list.add(url);
        map.put(interfaceName, list);
    }
    public static List<URL> get(String interfaceName){
        return map.get(interfaceName);
    }
}
