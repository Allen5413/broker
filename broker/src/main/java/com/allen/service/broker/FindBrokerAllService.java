package com.allen.service.broker;

import com.alibaba.fastjson.JSONObject;

import java.util.List;

/**
 * Created by Allen on 2017/10/18.
 */
public interface FindBrokerAllService {
    public List<JSONObject> find()throws Exception;
}
