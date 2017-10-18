package com.allen.service.broker;

import com.alibaba.fastjson.JSONObject;

import java.util.List;

/**
 * Created by Allen on 2017/10/18 0018.
 */
public interface RecommendBrokerService {
    public List<JSONObject> find(String schoolCode, long projectId)throws Exception;
}
