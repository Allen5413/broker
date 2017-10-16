package com.allen.service.broker.impl;

import com.alibaba.fastjson.JSONObject;
import com.allen.base.exception.BusinessException;
import com.allen.dao.PageInfo;
import com.allen.dao.broker.FindBrokerDao;
import com.allen.entity.broker.Broker;
import com.allen.service.attop.AttopService;
import com.allen.service.broker.PageBrokerService;
import com.allen.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by Allen on 2017/10/10.
 */
@Service
public class PageBrokerServiceImpl implements PageBrokerService {

    @Autowired
    private FindBrokerDao findBrokerDao;
    @Autowired
    private AttopService attopService;

    public PageInfo find(PageInfo pageInfo, Map<String, Object> paramsMap, Map<String, Boolean> sortMap)throws Exception{
        pageInfo = findBrokerDao.findPage(pageInfo, paramsMap, sortMap);
        if(pageInfo.getPageResults() != null && 0 < pageInfo.getPageResults().size()){
            List<JSONObject> list = new ArrayList<JSONObject>(pageInfo.getPageResults().size());
            for(int i=0; i<pageInfo.getPageResults().size(); i++){
                Broker broker = (Broker) pageInfo.getPageResults().get(i);
                JSONObject json = new JSONObject();
                json.put("id", broker.getId());
                json.put("zz", broker.getZz());
                json.put("isBlack", broker.getIsBlack());
                json.put("blackReason", broker.getBlackReason());
                json.put("blackTime", broker.getBlackTime().toString().substring(0,10));
                json.put("blackType", broker.getBlackType());
                if(Broker.BLACKTYPE_SIXMONTH == broker.getBlackType()){
                    //得到6个月后的日期
                    json.put("removeTime", DateUtil.getAfterMonth(broker.getBlackTime(), 6));
                }

                JSONObject attopJSON = attopService.findZzInfo(broker.getZz(), "");
                if("0".equals(attopJSON.get("status"))){
                    throw new BusinessException("接口获取学校信息失败！");
                }
                List schoolList = (List) attopJSON.get("data");
                if(schoolList != null && 0 < schoolList.size()){
                    JSONObject userSchool = (JSONObject) schoolList.get(0);
                    json.put("name", userSchool.get("realname"));
                }

                list.add(json);
            }
            pageInfo.setPageResults(list);
        }
        return pageInfo;
    }
}
