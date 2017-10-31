package com.allen.service.brokerproject;

import com.alibaba.fastjson.JSONObject;

import java.util.List;

/**
 * Created by Allen on 2017/10/31.
 */
public interface FindBrokerAndCustomerNumByProjectIdAndRandomService {
    public List<JSONObject> find(long projectId, String[] indexs)throws Exception;
}
