package com.gupiaopiao.util;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.gupiaopiao.bean.HttpBean;

import java.lang.reflect.Type;


public class BeanUtil {

    public static <E> HttpBean<E> createErrorBean(String msg) {
        HttpBean<E> bean = new HttpBean<>();
        bean.setSucceed(0);
        bean.setMsg(msg);
        return bean;
    }

    public static <E> HttpBean<E> createNormalBean() {
        HttpBean<E> bean = new HttpBean<>();
        bean.setSucceed(1);
        bean.setMsg("请求成功");
        return bean;
    }

    public static String beanToJsonString(Object src) {
        Gson gson = new Gson();
        return gson.toJson(src);
    }

    public static String beanToJsonString(Object src, Type type) {
        Gson gson = new Gson();
        return gson.toJson(src, type);
    }

    public static JsonElement beanToJson(Object src) {
        Gson gson = new Gson();
        return gson.toJsonTree(src);
    }

    public static JsonElement beanToJson(Object src, Type type) {
        Gson gson = new Gson();
        return gson.toJsonTree(src, type);
    }
}
