package com.allen.service.app.customer.impl;

import com.alibaba.fastjson.JSONObject;
import com.allen.base.exception.BusinessException;
import com.allen.dao.PageInfo;
import com.allen.dao.customer.FindCustomerDao;
import com.allen.service.app.customer.FindCustomerByBrokerIdForAppService;
import com.allen.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by Allen on 2017/10/23 0023.
 */
@Service
public class FindCustomerByBrokerIdForAppServiceImpl implements FindCustomerByBrokerIdForAppService {

    @Autowired
    private FindCustomerDao findCustomerDao;

    @Override
    public JSONObject find(HttpServletRequest request) throws Exception {
        JSONObject jsonObject = new JSONObject();
        String id = request.getParameter("id");
        String projectId = request.getParameter("projectId");
        int pageNum = StringUtil.isEmpty(request.getParameter("pageNum")) ? 1 : Integer.parseInt(request.getParameter("pageNum"));
        int pageSize = StringUtil.isEmpty(request.getParameter("pageSize")) ? 10 : Integer.parseInt(request.getParameter("pageSize"));
        if(StringUtil.isEmpty(id)){
            throw new BusinessException("没有传入经纪人id");
        }
        if(StringUtil.isEmpty(projectId)){
            throw new BusinessException("没有传入项目id");
        }
        PageInfo pageInfo = new PageInfo();
        pageInfo.setCurrentPage(1);
        pageInfo.setCountOfCurrentPage(9999999);
        Map<String, Object> paramsMap = new HashMap<String, Object>(2);
        paramsMap.put("c.broker_id", Long.parseLong(id));
        paramsMap.put("c.project_id", Long.parseLong(projectId));
        Map<String, Boolean> sortMap = new LinkedHashMap<String, Boolean>(2);
        sortMap.put("c.is_star", false);
        sortMap.put("c.id", false);
        long num = findCustomerDao.findPage(pageInfo, paramsMap, sortMap).getTotalCount();
        pageInfo.setCurrentPage(pageNum);
        pageInfo.setCountOfCurrentPage(pageSize);
        pageInfo = findCustomerDao.findPage(pageInfo, paramsMap, sortMap);
        jsonObject.put("customerList", pageInfo.getPageResults());
        jsonObject.put("num", num);
        jsonObject.put("status", 1);
        return jsonObject;
    }
}
