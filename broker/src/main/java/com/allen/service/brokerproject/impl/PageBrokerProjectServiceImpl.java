package com.allen.service.brokerproject.impl;

import com.alibaba.fastjson.JSONObject;
import com.allen.base.exception.BusinessException;
import com.allen.dao.PageInfo;
import com.allen.dao.brokerproject.FindBrokerProjectDao;
import com.allen.service.attop.AttopService;
import com.allen.service.brokerproject.PageBrokerProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Allen on 2017/10/10.
 */
@Service
public class PageBrokerProjectServiceImpl implements PageBrokerProjectService {

    @Autowired
    private FindBrokerProjectDao findBrokerProjectDao;
    @Autowired
    private AttopService attopService;

    public PageInfo find(PageInfo pageInfo, Map<String, Object> paramsMap, Map<String, Boolean> sortMap)throws Exception{
        pageInfo = findBrokerProjectDao.findPage(pageInfo, paramsMap, sortMap);
        if(null != pageInfo && null != pageInfo.getPageResults() && 0 < pageInfo.getPageResults().size()){
            List<Map> list = pageInfo.getPageResults();
            List<Map> resultList = new ArrayList<Map>(list.size());
            for(Map map : list){
                JSONObject attopJSON = attopService.findZzInfo(map.get("creator").toString(), "");
                if ("0".equals(attopJSON.get("status"))) {
                    throw new BusinessException("接口获取学校信息失败！");
                }
                List schoolList = (List) attopJSON.get("data");
                if(schoolList != null && 0 < schoolList.size()){
                    JSONObject userSchool = (JSONObject) schoolList.get(0);
                    map.put("name", userSchool.get("realname"));
                    resultList.add(map);
                }
            }
            pageInfo.setPageResults(resultList);
        }
        return pageInfo;
    }
}
