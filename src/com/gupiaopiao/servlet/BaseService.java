package com.gupiaopiao.servlet;

import com.google.gson.JsonObject;
import com.gupiaopiao.bean.HttpBean;
import com.gupiaopiao.util.BeanUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;

public abstract class BaseService<T> extends HttpServlet {
    private Type mBeanType;

    @Override
    public void init() throws ServletException {
        super.init();
        getParameterizedType();
    }

    private void getParameterizedType() {
        try {
            ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
            mBeanType = genericSuperclass.getActualTypeArguments()[0];
        } catch (Exception e) {
            e.printStackTrace();
            mBeanType = null;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Set<Map.Entry<String, String[]>> entries = req.getParameterMap().entrySet();
        System.out.println("request incomming------------------------------------------------");
        for (Map.Entry<String, String[]> entry : entries) {
            System.out.println("key : " + entry.getKey() + "   value : " + Arrays.toString(entry.getValue()));
        }
        System.out.println("request end------------------------------------------------------");
        resp.setCharacterEncoding("utf-8");
        PrintWriter writer = resp.getWriter();
        Object obj = getResponseBodyBean(req);
        String responseString;
        if (obj instanceof String) {
            HttpBean response = BeanUtil.createErrorBean(obj.toString());
            responseString = BeanUtil.beanToJsonString(response);
        } else {
            if (mBeanType == null) {
                HttpBean response = BeanUtil.createErrorBean("服务器接口调用失败");
                responseString = BeanUtil.beanToJsonString(response);
            } else {
                JsonObject responseJson = BeanUtil.beanToJson(BeanUtil.createNormalBean()).getAsJsonObject();
                responseJson.add("data", BeanUtil.beanToJson(obj, mBeanType));
                responseString = responseJson.toString();
            }
        }
        writer.flush();
        writer.print(responseString);
        writer.close();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    /**
     * @return 返回String代表本次请求失败, String为失败的msg;返回T代表本次请求成功
     */
    protected abstract Object getResponseBodyBean(HttpServletRequest req);

    @Override
    public void destroy() {
        super.destroy();
    }
}
