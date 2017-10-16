package com.allen.service.broker;

import com.alibaba.fastjson.JSONObject;
import com.allen.entity.broker.Broker;

/**
 * Created by Allen on 2017/10/12.
 */
public interface FindBrokerByZZService {
    public Broker find(String zz)throws Exception;
    public JSONObject findAttop(String zz)throws Exception;
}
