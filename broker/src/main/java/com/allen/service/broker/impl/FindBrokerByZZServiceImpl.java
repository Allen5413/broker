package com.allen.service.broker.impl;

import com.alibaba.fastjson.JSONObject;
import com.allen.base.exception.BusinessException;
import com.allen.dao.broker.BrokerDao;
import com.allen.dao.customer.CustomerDao;
import com.allen.entity.broker.Broker;
import com.allen.service.attop.AttopService;
import com.allen.service.broker.FindBrokerByZZService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;

/**
 * Created by Allen on 2017/10/16.
 */
@Service
public class FindBrokerByZZServiceImpl implements FindBrokerByZZService{

    @Autowired
    private AttopService attopService;
    @Autowired
    private CustomerDao customerDao;
    @Autowired
    private BrokerDao brokerDao;

    @Override
    public Broker find(String zz) throws Exception {
        return brokerDao.findByZz(zz);
    }

    @Override
    public JSONObject findAttop(String zz) throws Exception {
        JSONObject json = attopService.findZzInfo(zz, "");
        if ("0".equals(json.get("status"))) {
            throw new BusinessException("接口获取学校信息失败！");
        }
        List list = (List) json.get("data");
        if(list != null && 0 < list.size()){
            for(int i=0; i<list.size(); i++){
                JSONObject userSchool = (JSONObject) list.get(i);
                Broker broker = brokerDao.findByZz(zz);
                if(null != broker) {
                    BigInteger customerNum = customerDao.findByBrokerId(broker.getId());
                    userSchool.put("customerNum", null == customerNum ? 0 : customerNum);
                }
                return userSchool;
            }
        }
        return null;
    }
}
