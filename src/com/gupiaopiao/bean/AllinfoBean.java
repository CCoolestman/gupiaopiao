package com.gupiaopiao.bean;

import java.io.Serializable;

public class AllinfoBean implements Serializable {
    /**
     * 代码
     */
    private String code;
    /**
     * 名称
     */
    private String name;
    /**
     * 最新价格
     */
    private String lastPrice;
    /**
     * 涨跌幅
     */
    private String quoteChange;
    /**
     * 股东户数增减比例
     */
    private String proportion;

    /**
     * 股东户数统计截至日期
     */
    private String date;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastPrice() {
        return lastPrice;
    }

    public void setLastPrice(String lastPrice) {
        this.lastPrice = lastPrice;
    }

    public String getQuoteChange() {
        return quoteChange;
    }

    public void setQuoteChange(String quoteChange) {
        this.quoteChange = quoteChange;
    }

    public String getProportion() {
        return proportion;
    }

    public void setProportion(String proportion) {
        this.proportion = proportion;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
