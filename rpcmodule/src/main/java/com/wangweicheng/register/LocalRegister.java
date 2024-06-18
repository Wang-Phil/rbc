package com.wangweicheng.register;
/*
 *@version 1.0
 *@Author: WangWeicheng
 *@Date: 2024/6/18
 *@Time: 22:32
 */

import java.util.HashMap;
import java.util.Map;

//本地注册
public class LocalRegister {
    private static Map<String,Class> map = new HashMap<>();
    public static void register(String interfaceName,Class implClass){
        map.put(interfaceName,implClass);
    }
    public static Class get(String interfaceName){
        return map.get(interfaceName);
    }
}
