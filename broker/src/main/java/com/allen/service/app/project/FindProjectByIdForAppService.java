package com.allen.service.app.project;

import com.alibaba.fastjson.JSONObject;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Allen on 2017/10/23 0023.
 */
public interface FindProjectByIdForAppService {
    public JSONObject find(HttpServletRequest request)throws Exception;
}
