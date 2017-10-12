package com.allen.service.brokerproject;

import com.alibaba.fastjson.JSONObject;

import java.util.List;
import java.util.Map;

/**
 * Created by Allen on 2017/10/12.
 */
public interface FindBrokerProjectBySchoolCodeService {
    public List<JSONObject> find(Long projectId, String schoolCode)throws Exception;
}
