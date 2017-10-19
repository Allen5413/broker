package com.allen.service.broker;

import com.alibaba.fastjson.JSONObject;

import java.util.List;

/**
 * Created by Allen on 2017/10/18 0018.
 */
public interface RecommendBrokerService {
    /**
     * 推荐同校的4个经纪人，如果没有4个，随机取其它学校的经纪人
     * @param schoolCode
     * @param projectId
     * @return
     * @throws Exception
     */
    public List<JSONObject> find(String schoolCode, long projectId)throws Exception;

    /**
     * 随机抽取指定数量的经纪人
     * @param projectId
     * @param num
     * @return
     * @throws Exception
     */
    public List<JSONObject> randomFind(long projectId, int num)throws Exception;
}
