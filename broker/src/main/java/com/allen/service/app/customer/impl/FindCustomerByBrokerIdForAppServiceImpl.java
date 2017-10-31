package com.allen.service.app.customer.impl;

import com.alibaba.fastjson.JSONObject;
import com.allen.base.exception.BusinessException;
import com.allen.dao.PageInfo;
import com.allen.dao.customer.FindCustomerDao;
import com.allen.service.app.customer.FindCustomerByBrokerIdForAppService;
import com.allen.service.broker.FindBrokerByZZService;
import com.allen.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * Created by Allen on 2017/10/23 0023.
 */
@Service
public class FindCustomerByBrokerIdForAppServiceImpl implements FindCustomerByBrokerIdForAppService {

    @Autowired
    private FindCustomerDao findCustomerDao;
    @Autowired
    private FindBrokerByZZService findBrokerByZZService;

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
        Map<String, Object> paramsMap = new HashMap<String, Object>(2);
        paramsMap.put("c.broker_id", Long.parseLong(id));
        paramsMap.put("c.project_id", Long.parseLong(projectId));
        Map<String, Boolean> sortMap = new LinkedHashMap<String, Boolean>(2);
        sortMap.put("c.is_star", false);
        sortMap.put("c.end_login_time", true);
        pageInfo.setCurrentPage(pageNum);
        pageInfo.setCountOfCurrentPage(pageSize);
        pageInfo = findCustomerDao.findPage(pageInfo, paramsMap, sortMap);
        if(null != pageInfo.getPageResults() && 0 < pageInfo.getPageResults().size()){
            List<Map> list = pageInfo.getPageResults();
            List<Map> newList = new ArrayList<Map>(list.size());
            for(Map map : list){
                JSONObject json = findBrokerByZZService.findAttop(map.get("zz").toString());
                map.put("name", json.get("name"));
                map.put("nickName", json.get("nickname"));
                map.put("icon", json.get("icon"));
                newList.add(map);
            }
            pageInfo.setPageResults(newList);
        }
        jsonObject.put("customerList", pageInfo.getPageResults());
        jsonObject.put("num", pageInfo.getTotalCount());
        jsonObject.put("status", 1);
        return jsonObject;
    }
}
