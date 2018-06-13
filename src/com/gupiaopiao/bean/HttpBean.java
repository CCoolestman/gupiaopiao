package com.gupiaopiao.bean;

import java.io.Serializable;

public class HttpBean<T> implements Serializable {
    private int succeed;
    private String msg;
    private T data;

    public int getSucceed() {
        return succeed;
    }

    public void setSucceed(int succeed) {
        this.succeed = succeed;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
