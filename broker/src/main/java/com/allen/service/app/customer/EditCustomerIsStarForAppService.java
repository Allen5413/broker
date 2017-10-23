package com.allen.service.app.customer;

import com.alibaba.fastjson.JSONObject;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Allen on 2017/10/23 0023.
 */
public interface EditCustomerIsStarForAppService {
    public JSONObject edit(HttpServletRequest request)throws Exception;
}
