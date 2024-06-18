package com.wangweicheng.protocal;
/*
 *@version 1.0
 *@Author: WangWeicheng
 *@Date: 2024/6/18
 *@Time: 22:18
 */

import com.wangweicheng.common.Invocation;
import com.wangweicheng.register.LocalRegister;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


public class HttpServerHandler {
    public void handler(HttpServletRequest req, HttpServletResponse resp) {
        //处理请求，-->接口、方法、方法参数
        try {
           Invocation invocation = (Invocation) new ObjectInputStream(req.getInputStream()).readObject();
            String interfaceName = invocation.getInterfaceName();

            Class classImpl = LocalRegister.get(interfaceName, "1.0");
            Method method = classImpl.getMethod(invocation.getMethodName(), invocation.getParametersTypes());
            String result = (String) method.invoke(classImpl.newInstance(), invocation.getParameters());

            //写到resp中
            IOUtils.write(result, resp.getOutputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        }
    }
}
