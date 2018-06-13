package com.gupiaopiao.servlet;

import com.gupiaopiao.bean.AllinfoBean;
import com.gupiaopiao.bean.ListBean;
import com.gupiaopiao.dao.AllinfoDao;
import com.gupiaopiao.util.NumberUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class AllinfoService extends BaseService<ListBean<AllinfoBean>> {

    @Override
    protected Object getResponseBodyBean(HttpServletRequest req) {
        Float proportion = NumberUtil.parseFloat(req.getParameter("proportion"));
        Float quoteChange = NumberUtil.parseFloat(req.getParameter("quoteChange"));
        int proportionOrder = NumberUtil.parseInt(req.getParameter("proportionOrder"), 1);
        int quoteChangeOrder = NumberUtil.parseInt(req.getParameter("quoteChangeOrder"), 1);
        Integer date = NumberUtil.parseInt(req.getParameter("date"), 20);
        int page = NumberUtil.parseInt(req.getParameter("page"), 1);
        int pageSize = NumberUtil.parseInt(req.getParameter("pageSize"), 20);
        AllinfoDao allinfoDao = new AllinfoDao();
        List<AllinfoBean> allinfo = allinfoDao.getAllinfo(proportion, proportionOrder, quoteChange, quoteChangeOrder, date, page, pageSize);
        ListBean<AllinfoBean> bean = new ListBean<>();
        bean.setData(allinfo);
        bean.setPage(page);
        bean.setPageSize(pageSize);
        bean.setTotal(allinfoDao.getTotal(proportion, proportionOrder, quoteChange, quoteChangeOrder, date));
        return bean;
    }
}
