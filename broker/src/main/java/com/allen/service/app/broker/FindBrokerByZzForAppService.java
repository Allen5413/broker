package com.allen.service.app.broker;

import com.alibaba.fastjson.JSONObject;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Allen on 2017/10/23 0023.
 */
public interface FindBrokerByZzForAppService {
    public JSONObject find(String zz)throws Exception;

    /**
     * 查询zz是否是经纪人
     * @param zz
     * @return
     * @throws Exception
     */
    public JSONObject isBroker(HttpServletRequest request)throws Exception;
}
