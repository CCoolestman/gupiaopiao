package com.gupiaopiao.bean;

import java.io.Serializable;

public class DetailBean implements Serializable {
    /**
     * 代码
     */
    private String code;
    /**
     * 名称
     */
    private String name;

    /**
     * 股东户数统计截止日期
     */
    private String date;

    /**
     * 股票价格
     */
    private String stockPrice;
    /**
     * 区间涨跌幅
     */
    private String quoteChange;

    /**
     * 股东户数
     */
    private String shareholders;

    /**
     * 股东户数增减比例
     */
    private String proportion;

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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStockPrice() {
        return stockPrice;
    }

    public void setStockPrice(String stockPrice) {
        this.stockPrice = stockPrice;
    }

    public String getQuoteChange() {
        return quoteChange;
    }

    public void setQuoteChange(String quoteChange) {
        this.quoteChange = quoteChange;
    }

    public String getShareholders() {
        return shareholders;
    }

    public void setShareholders(String shareholders) {
        this.shareholders = shareholders;
    }

    public String getProportion() {
        return proportion;
    }

    public void setProportion(String proportion) {
        this.proportion = proportion;
    }
}
