package com.allen.service.brokerproject;

import com.alibaba.fastjson.JSONObject;

import java.util.List;
import java.util.Map;

/**
 * Created by Allen on 2017/10/12.
 */
public interface FindBrokerProjectBySchoolCodeService {
    /**
     * 查询审核通过的经纪人
     * @param projectId
     * @param schoolCode
     * @return
     * @throws Exception
     */
    public List<JSONObject> find(Long projectId, String schoolCode)throws Exception;

    /**
     * 查询审核通过和待审核的经纪人
     * @param projectId
     * @param schoolCode
     * @return
     * @throws Exception
     */
    public List<JSONObject> findWaitAndPass(Long projectId, String schoolCode)throws Exception;
}
