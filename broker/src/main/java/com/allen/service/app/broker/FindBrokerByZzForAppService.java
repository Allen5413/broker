package com.allen.service.app.broker;

import com.alibaba.fastjson.JSONObject;

/**
 * Created by Allen on 2017/10/23 0023.
 */
public interface FindBrokerByZzForAppService {
    public JSONObject find(String zz)throws Exception;
}
