package com.allen.service.app.broker;

import com.alibaba.fastjson.JSONObject;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by Allen on 2017/11/27.
 */
public interface FindBrokerBySchoolNoForAppService {
    /**
     * 查询指定数量的一个学校的经纪人
     * @param request
     * @return
     * @throws Exception
     */
    public JSONObject find(HttpServletRequest request)throws Exception;
}
