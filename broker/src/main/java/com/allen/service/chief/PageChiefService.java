package com.allen.service.chief;

import com.allen.dao.PageInfo;

import java.util.Map;

/**
 * Created by Allen on 2017/10/10.
 */
public interface PageChiefService {
    public PageInfo find(PageInfo pageInfo, Map<String, String> paramsMap)throws Exception;
}
