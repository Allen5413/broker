package com.allen.service.broker;

import com.alibaba.fastjson.JSONObject;
import com.allen.entity.broker.Broker;

/**
 * Created by Allen on 2017/10/20 0020.
 */
public interface FindBrokerByIdService {
    public Broker find(long id)throws Exception;
    public JSONObject findAttop(long id)throws Exception;
}
