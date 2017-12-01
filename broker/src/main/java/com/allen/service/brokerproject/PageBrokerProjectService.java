package com.allen.service.brokerproject;

import com.allen.dao.PageInfo;

import java.util.Map;

/**
 * Created by Allen on 2017/10/10.
 */
public interface PageBrokerProjectService {
    public PageInfo find(PageInfo pageInfo, Map<String, Object> paramsMap, Map<String, Boolean> sortMap)throws Exception;

    public PageInfo findForSchool(PageInfo pageInfo, Map<String, Object> paramsMap, Map<String, Boolean> sortMap)throws Exception;
}
