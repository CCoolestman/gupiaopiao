package com.gupiaopiao.dao;

import com.gupiaopiao.bean.AllinfoBean;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AllinfoDao extends BaseDao {

    public List<AllinfoBean> getAllinfo(Float proportion, int proportionOrder, Float quoteChange, int quoteChangeOrder,
                                        int page, int pagesize) {
        openConnection();
        if (conn == null) {
            return null;
        }
        Statement stmt = null;
        ResultSet result = null;
        try {
            stmt = conn.createStatement();
            StringBuilder sqlBuilder = new StringBuilder();
            sqlBuilder.append("select * from allinfo ");

            if (quoteChange != null) {
                String order = quoteChangeOrder == 1 ? "> " : "< ";
                sqlBuilder.append("where 涨跌幅 ").append(order).append(quoteChange).append(" ");
            }
            if (proportion != null) {
                String order = proportionOrder == 1 ? "> " : "< ";
                sqlBuilder.append("and 股东户数增减比例 ").append(order).append(proportion).append(" ");
            }

            sqlBuilder.append("limit ").append((page - 1) * pagesize).append(",")
                    .append(pagesize);
            sqlBuilder.append(";");
            System.out.println(sqlBuilder);
            result = stmt.executeQuery(sqlBuilder.toString());
            List<AllinfoBean> alinfoList = new ArrayList<>();
            while (result.next()) {
                AllinfoBean allinfoBean = new AllinfoBean();
                allinfoBean.setCode(result.getString("代码"));
                allinfoBean.setName(result.getString("名称"));
                allinfoBean.setLastPrice(result.getString("最新价格"));
                allinfoBean.setQuoteChange(result.getString("涨跌幅"));
                allinfoBean.setProportion(result.getString("股东户数增减比例"));
                allinfoBean.setDate(result.getString("股东户数统计截至日期"));
                alinfoList.add(allinfoBean);
            }
            result.close();
            stmt.close();
            return alinfoList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            closeAll(stmt, result);
        }
    }

    public int getTotal(Float proportion, int proportionOrder, Float quoteChange, int quoteChangeOrder) {
        openConnection();
        if (conn == null) {
            return 0;
        }
        Statement stmt = null;
        ResultSet result = null;
        try {
            stmt = conn.createStatement();
            StringBuilder sqlBuilder = new StringBuilder();
            sqlBuilder.append("select count(*) from allinfo ");

            if (quoteChange != null) {
                String order = quoteChangeOrder == 1 ? "> " : "< ";
                sqlBuilder.append("where 涨跌幅 ").append(order).append(quoteChange).append(" ");
            }
            if (proportion != null) {
                String order = proportionOrder == 1 ? "> " : "< ";
                sqlBuilder.append("and 股东户数增减比例 ").append(order).append(proportion).append(" ");
            }

            sqlBuilder.append(";");

            result = stmt.executeQuery(sqlBuilder.toString());
            result.next();
            int count = result.getInt(1);
            result.close();
            stmt.close();
            return count;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        } finally {
            closeAll(stmt, result);
        }
    }
}
