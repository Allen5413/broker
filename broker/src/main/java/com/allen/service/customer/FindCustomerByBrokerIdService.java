package com.allen.service.customer;

import com.alibaba.fastjson.JSONObject;

import java.util.List;

/**
 * Created by Allen on 2017/10/12.
 */
public interface FindCustomerByBrokerIdService {
    public List<JSONObject> find(long brokerId, long projectId)throws Exception;
}
