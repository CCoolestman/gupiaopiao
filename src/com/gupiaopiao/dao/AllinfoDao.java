package com.gupiaopiao.dao;

import com.gupiaopiao.bean.AllinfoBean;

import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class AllinfoDao extends BaseDao {

    public List<AllinfoBean> getAllinfo(Float proportion, Integer proportionOrder, Float quoteChange, Integer quoteChangeOrder,
                                        Integer date, int page, int pagesize) {
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

            buildWhere(proportion, proportionOrder, quoteChange, quoteChangeOrder, sqlBuilder, date);

            sqlBuilder.append("limit ").append((page - 1) * pagesize).append(",")
                    .append(pagesize);
            sqlBuilder.append(";");
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

    public int getTotal(Float proportion, Integer proportionOrder, Float quoteChange, Integer quoteChangeOrder,
                        Integer date) {
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

            buildWhere(proportion, proportionOrder, quoteChange, quoteChangeOrder, sqlBuilder, date);

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

    private void buildWhere(Float proportion, Integer proportionOrder, Float quoteChange, Integer quoteChangeOrder, StringBuilder sqlBuilder,
                            Integer date) {
        if (quoteChange != null || proportion != null || date != null) {
            sqlBuilder.append("where ");
        }

        if (quoteChange != null) {
            String order = quoteChangeOrder == 1 ? "> " : "< ";
            sqlBuilder.append("涨跌幅 ").append(order).append(quoteChange).append(" ");
        }
        if (proportion != null) {
            String order = proportionOrder == 1 ? "> " : "< ";
            if (quoteChange != null) {
                sqlBuilder.append("and ");
            }
            sqlBuilder.append("股东户数增减比例 ").append(order).append(proportion).append(" ");
        }
        if (date != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Calendar now = Calendar.getInstance();
            String end = sdf.format(now.getTime());
            now.set(Calendar.MONTH, now.get(Calendar.MONTH) - date);
            String start = sdf.format(now.getTime());
            if (quoteChange != null || proportion != null) {
                sqlBuilder.append("and ");
            }
            sqlBuilder.append("股东户数统计截至日期 between '").append(start).append("' and '").append(end).append("' ");
        }
    }
}
