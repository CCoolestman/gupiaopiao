package com.gupiaopiao.dao;

import com.gupiaopiao.bean.DetailBean;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DetailDao extends BaseDao {

    public List<DetailBean> getDetails(int code) {
        openConnection();
        if (conn == null) {
            return null;
        }
        Statement stmt = null;
        ResultSet result = null;
        try {
            stmt = conn.createStatement();
            String sql = "select * from detail where 代码 = " + code + ";";
            result = stmt.executeQuery(sql);
            List<DetailBean> data = new ArrayList<>();
            while (result.next()) {
                DetailBean bean = new DetailBean();
                bean.setCode(result.getString("代码"));
                bean.setName(result.getString("名称"));
                bean.setDate(result.getString("股东户数统计截止日期"));
                bean.setStockPrice(result.getString("股票价格"));
                bean.setQuoteChange(result.getString("区间涨跌幅"));
                bean.setShareholders(result.getString("股东户数"));
                bean.setProportion(result.getString("股东户数增减比例"));
                data.add(bean);
            }
            result.close();
            stmt.close();
            return data;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            closeAll(stmt, result);
        }
    }
}
