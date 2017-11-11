package com.allen.youxue.service.team;

import com.allen.dao.PageInfo;

import java.util.Map;

/**
 * Created by Allen on 2017/10/18.
 */
public interface PageYxTeamService {
    public PageInfo findPage(PageInfo pageInfo, Map<String, Object> paramsMap, Map<String, Boolean> sortMap, boolean isCountTeamNum)throws Exception;
    public PageInfo findPageForAttop(PageInfo pageInfo, Map<String, Object> paramsMap, Map<String, Boolean> sortMap, boolean isCountTeamNum)throws Exception;
}
