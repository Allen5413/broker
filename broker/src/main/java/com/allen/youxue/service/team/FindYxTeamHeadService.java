package com.allen.youxue.service.team;

import com.alibaba.fastjson.JSONObject;

import java.util.List;

/**
 * Created by Allen on 2017/10/18.
 */
public interface FindYxTeamHeadService {
    public List<JSONObject> find()throws Exception;
    public int countNum()throws Exception;
}
