package com.allen.service.broker.impl;

import com.alibaba.fastjson.JSONObject;
import com.allen.base.exception.BusinessException;
import com.allen.dao.broker.BrokerDao;
import com.allen.entity.broker.Broker;
import com.allen.service.attop.AttopService;
import com.allen.service.broker.FindBrokerByIdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Allen on 2017/10/20 0020.
 */
@Service
public class FindBrokerByIdServiceImpl implements FindBrokerByIdService {

    @Autowired
    private AttopService attopService;
    @Autowired
    private BrokerDao brokerDao;

    @Override
    public Broker find(long id) throws Exception {
        return brokerDao.findOne(id);
    }

    @Override
    public JSONObject findAttop(long id) throws Exception {
        String zz = this.find(id).getZz();
        JSONObject json = attopService.findZzInfo(zz, "");
        if ("0".equals(json.get("status"))) {
            throw new BusinessException("接口获取学校信息失败！");
        }
        List list = (List) json.get("data");
        if(list != null && 0 < list.size()){
            for(int i=0; i<list.size(); i++){
                JSONObject userSchool = (JSONObject) list.get(i);
                userSchool.put("id", id);
                return userSchool;
            }
        }
        return null;
    }
}
