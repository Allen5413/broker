package com.allen.web.controller;

import com.allen.dao.PageInfo;
import com.allen.util.StringUtil;

import javax.servlet.http.HttpServletRequest;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Created by Allen on 2016/12/20.
 */
public class BaseController {

    protected PageInfo getPageInfo(HttpServletRequest request) {
        PageInfo pageinfo = new PageInfo();
        pageinfo.setCurrentPage(1);
        pageinfo.setCountOfCurrentPage(10);
        String page = request.getParameter("page");
        if(!StringUtil.isEmpty(page)) {
            pageinfo.setCurrentPage(Integer.parseInt(page));
        }
        String rows = request.getParameter("rows");
        if(!StringUtil.isEmpty(rows)) {
            pageinfo.setCountOfCurrentPage(Integer.parseInt(rows));
        }
        return pageinfo;
    }

    protected String getParameters(HttpServletRequest request)throws Exception{
        String str = "{";
        Map<String, String[]> map = request.getParameterMap();
        Iterator it = map.keySet().iterator();
        while(it.hasNext()){
            String name = (String) it.next();
            String value = request.getParameter(name);
            str += "\""+name+"\":"+(StringUtil.isEmpty(value) ? "\"\"" : "\""+value+"\"")+",";
        }
        if(!"{".equals(str)) {
            str = str.substring(0, str.length() - 1);
        }
        str += "}";
        return URLEncoder.encode(str);
    }
}
