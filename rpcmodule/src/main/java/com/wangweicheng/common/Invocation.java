package com.wangweicheng.common;
/*
 *@version 1.0
 *@Author: WangWeicheng
 *@Date: 2024/6/18
 *@Time: 22:23
 */

import java.io.Serializable;

//让接口支持序列化
public class Invocation implements Serializable {
    //调用对象的接口，方法，参数
    private String interfaceName;           //接口名
    private String methodName;              //方法名
    private Class[] parametersTypes;        //参数类型
    private Object[] parameters;            //参数

    public Invocation(String interfaceName, String methodName, Class[] parametersTypes, Object[] parameters) {
        this.interfaceName = interfaceName;
        this.methodName = methodName;
        this.parametersTypes = parametersTypes;
        this.parameters = parameters;
    }

    public String getInterfaceName() {
        return interfaceName;
    }

    public void setInterfaceName(String interfaceName) {
        this.interfaceName = interfaceName;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Class[] getParametersTypes() {
        return parametersTypes;
    }

    public void setParametersTypes(Class[] parametersTypes) {
        this.parametersTypes = parametersTypes;
    }

    public Object[] getParameters() {
        return parameters;
    }

    public void setParameters(Object[] parameters) {
        this.parameters = parameters;
    }
}
