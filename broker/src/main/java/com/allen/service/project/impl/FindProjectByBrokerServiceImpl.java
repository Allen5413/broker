package com.allen.service.project.impl;

import com.allen.dao.project.FindProjectDao;
import com.allen.service.project.FindProjectByBrokerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Allen on 2017/10/10.
 */
@Service
public class FindProjectByBrokerServiceImpl implements FindProjectByBrokerService {

    @Autowired
    private FindProjectDao findProjectDao;

    @Override
    public List<Map> find(long brokerId) throws Exception {
        java.util.Map<String, Object> paramsMap = new HashMap<String, Object>(1);
        paramsMap.put("bp.broker_id", brokerId);
        Map<String, Boolean> sortMap = new HashMap<String, Boolean>(1);
        sortMap.put("p.id", true);
        return findProjectDao.findByBroker(paramsMap, sortMap);
    }
}
