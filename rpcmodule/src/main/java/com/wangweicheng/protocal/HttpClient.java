package com.wangweicheng.protocal;
/*
 *@version 1.0
 *@Author: WangWeicheng
 *@Date: 2024/6/19
 *@Time: 16:27
 */

import com.wangweicheng.common.Invocation;
import org.apache.commons.io.IOUtils;


import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class HttpClient {
    public String send(String hostname, Integer port, Invocation invocation) {
        //用户的配置
        try{
            URL url = new URL("http" , hostname,  port, "/");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("POST");
            connection.setDoOutput(true);

            //配置
            OutputStream outputStream = connection.getOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);

            objectOutputStream.writeObject(invocation);
            objectOutputStream.flush();
            objectOutputStream.close();

            InputStream inputStream = connection.getInputStream();
            String result = IOUtils.toString(inputStream);
            return result;
        }catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
