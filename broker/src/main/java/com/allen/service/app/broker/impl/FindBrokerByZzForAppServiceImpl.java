package com.allen.service.app.broker.impl;

import com.alibaba.fastjson.JSONObject;
import com.allen.dao.PageInfo;
import com.allen.dao.brokerproject.FindBrokerProjectDao;
import com.allen.dao.project.ProjectDao;
import com.allen.entity.broker.Broker;
import com.allen.service.app.broker.FindBrokerByZzForAppService;
import com.allen.service.broker.FindBrokerByZZService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Allen on 2017/10/23 0023.
 */
@Service
public class FindBrokerByZzForAppServiceImpl implements FindBrokerByZzForAppService {

    @Autowired
    private FindBrokerByZZService findBrokerByZZService;
    @Autowired
    private FindBrokerProjectDao findBrokerProjectDao;
    @Autowired
    private ProjectDao projectDao;

    @Override
    public JSONObject find(String zz) throws Exception {
        JSONObject jsonObject = new JSONObject();
        Broker broker = findBrokerByZZService.find(zz);
        if(null == broker){
            jsonObject.put("isBroker", 0);
        }else{
            jsonObject.put("isBroker", 1);
            jsonObject.put("id", broker.getId());

            //获取经纪人关联的项目
            Map<String, Object> paramsMap = new HashMap<String, Object>(1);
            paramsMap.put("bp.broker_id", broker.getId());
            Map<String, Boolean> sortMap = new HashMap<String, Boolean>(1);
            sortMap.put("bp.id", false);
            PageInfo pageInfo = new PageInfo();
            pageInfo.setCurrentPage(1);
            pageInfo.setCountOfCurrentPage(999999);
            pageInfo = findBrokerProjectDao.findPage(pageInfo, paramsMap, sortMap);
            jsonObject.put("brokerProjectList", pageInfo.getPageResults());

            //获取所有项目
            jsonObject.put("projectList", projectDao.findAll());

            jsonObject.put("status", 1);
        }
        return jsonObject;
    }
}
