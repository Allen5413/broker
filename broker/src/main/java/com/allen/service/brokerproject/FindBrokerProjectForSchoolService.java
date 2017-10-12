package com.allen.service.brokerproject;

import com.alibaba.fastjson.JSONObject;

import java.util.Map;

/**
 * Created by Allen on 2017/10/12.
 */
public interface FindBrokerProjectForSchoolService {
    public Map<String, JSONObject> find(Long projectId, String name)throws Exception;
}
