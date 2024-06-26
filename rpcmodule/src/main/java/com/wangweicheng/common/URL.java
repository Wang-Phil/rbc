package com.wangweicheng.common;
/*
 *@version 1.0
 *@Author: WangWeicheng
 *@Date: 2024/6/19
 *@Time: 22:43
 */

import java.io.Serializable;

//支持序列化，存储到文件中
public class URL implements Serializable {
    private String hostname;
    private Integer port;

    public URL(String hostname, Integer port) {
        this.hostname = hostname;
        this.port = port;
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }
}
