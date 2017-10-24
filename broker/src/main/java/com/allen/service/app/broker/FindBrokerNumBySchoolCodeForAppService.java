package com.allen.service.app.broker;

import com.alibaba.fastjson.JSONObject;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Allen on 2017/10/24.
 */
public interface FindBrokerNumBySchoolCodeForAppService {
    public JSONObject find(HttpServletRequest request)throws Exception;
}
