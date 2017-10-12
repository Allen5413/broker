package com.allen.service.brokerproject.impl;

import com.allen.dao.PageInfo;
import com.allen.dao.brokerproject.FindBrokerProjectDao;
import com.allen.service.brokerproject.PageBrokerProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by Allen on 2017/10/10.
 */
@Service
public class PageBrokerProjectServiceImpl implements PageBrokerProjectService {

    @Autowired
    private FindBrokerProjectDao findBrokerProjectDao;

    public PageInfo find(PageInfo pageInfo, Map<String, Object> paramsMap, Map<String, Boolean> sortMap)throws Exception{
        return findBrokerProjectDao.findPage(pageInfo, paramsMap, sortMap);
    }
}
