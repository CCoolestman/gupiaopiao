package com.gupiaopiao.servlet;

import com.gupiaopiao.bean.DetailBean;
import com.gupiaopiao.dao.DetailDao;
import com.gupiaopiao.util.NumberUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class DetailService extends BaseService<List<DetailBean>> {
    @Override
    protected Object getResponseBodyBean(HttpServletRequest req) {
        Integer code = NumberUtil.parseInt(req.getParameter("code"));
        if (code == null) {
            return "code不能为空";
        }
        List<DetailBean> details = new DetailDao().getDetails(code);
        return details == null ? "数据库连接失败" : details;
    }
}
