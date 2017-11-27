package com.allen.service.app.chief;

import com.alibaba.fastjson.JSONObject;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Allen on 2017/11/27.
 */
public interface FindChiefBySchoolNoForAppService {
    /**
     * 查询一个学校的总监
     * @param request
     * @return
     * @throws Exception
     */
    public JSONObject find(HttpServletRequest request)throws Exception;
}
