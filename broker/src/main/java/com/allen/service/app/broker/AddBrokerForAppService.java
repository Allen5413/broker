package com.allen.service.app.broker;

import com.alibaba.fastjson.JSONObject;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Allen on 2017/10/19.
 */
public interface AddBrokerForAppService {
    public JSONObject add(HttpServletRequest request)throws Exception;
}
