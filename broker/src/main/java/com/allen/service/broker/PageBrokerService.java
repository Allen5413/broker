package com.allen.service.broker;

import com.allen.dao.PageInfo;

import java.util.Map;

/**
 * Created by Allen on 2017/10/10.
 */
public interface PageBrokerService {
    public PageInfo find(PageInfo pageInfo, Map<String, Object> paramsMap, Map<String, Boolean> sortMap)throws Exception;
}
