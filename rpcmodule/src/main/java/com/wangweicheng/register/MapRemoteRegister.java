package com.wangweicheng.register;

import com.wangweicheng.common.URL;

import java.io.*;
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
        //存到文件中
        saveFile();
    }
    public static List<URL> get(String interfaceName){
        //从文件中获取
        map = getFiles();
        return map.get(interfaceName);
    }

    private static void  saveFile(){
        try {
            FileOutputStream fos = new FileOutputStream("/temp.txt");
            ObjectOutputStream objectInputStream = new ObjectOutputStream(fos);
            objectInputStream.writeObject(map);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //通过文件来共享map
    private static Map<String, List<URL>> getFiles(){
        try {
            FileInputStream fis = new FileInputStream("/temp.txt");
            ObjectInputStream ois = new ObjectInputStream(fis);
            return (Map<String, List<URL>>) ois.readObject();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

}
