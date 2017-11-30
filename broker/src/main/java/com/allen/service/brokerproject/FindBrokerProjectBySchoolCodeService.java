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

    /**
     * 根据项目分组查询各个项目的经纪人情况
     * @param no
     * @return
     * @throws Exception
     */
    public List<Map> findGroupByProject(String no)throws Exception;
}
