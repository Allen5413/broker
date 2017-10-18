package com.allen.service.broker;

import com.alibaba.fastjson.JSONObject;
import com.allen.entity.broker.Broker;

import java.util.List;

/**
 * Created by Allen on 2017/10/18.
 */
public interface FindBrokerAllService {
    public List<Broker> find()throws Exception;
    public List<JSONObject> findAttop()throws Exception;
}
