package com.allen.service.broker.impl;

import com.alibaba.fastjson.JSONObject;
import com.allen.base.exception.BusinessException;
import com.allen.dao.broker.BrokerDao;
import com.allen.entity.broker.Broker;
import com.allen.service.attop.AttopService;
import com.allen.service.broker.FindBrokerAllService;
import com.allen.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Allen on 2017/10/18.
 */
@Service
public class FindBrokerAllServiceImpl implements FindBrokerAllService {

    @Autowired
    private BrokerDao brokerDao;
    @Autowired
    private AttopService attopService;

    @Override
    public List<Broker> find() throws Exception {
        return (List<Broker>) brokerDao.findAll();
    }

    @Override
    public List<JSONObject> findAttop() throws Exception {
        List<Broker> brokerList = brokerDao.findByIsBlack(Broker.ISBLACK_NOT);
        if(null != brokerList && 0 < brokerList.size()){
            List<JSONObject> list = new ArrayList<JSONObject>(brokerList.size());
            for(Broker broker : brokerList){
                JSONObject json = new JSONObject();

                JSONObject attopJSON = attopService.findZzInfo(broker.getZz(), "");
                if ("0".equals(attopJSON.get("status"))) {
                    throw new BusinessException("接口获取学生信息失败！");
                }
                List list2 = (List) attopJSON.get("data");
                if(list2 != null && 0 < list2.size()){
                    JSONObject userSchool = (JSONObject) list2.get(0);
                    if(null != userSchool.get("realname") && !StringUtil.isEmpty(userSchool.get("realname").toString())) {
                        json.put("id", broker.getId());
                        json.put("name", userSchool.get("realname").toString());
                        list.add(json);
                    }
                }

            }
            return list;
        }
        return null;
    }
}
