package com.allen.service.brokerproject.impl;

import com.allen.dao.brokerproject.BrokerProjectDao;
import com.allen.entity.brokerproject.BrokerProject;
import com.allen.service.brokerproject.FindBrokerProjectByBIdAndPIdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Allen on 2017/10/16.
 */
@Service
public class FindBrokerProjectByBIdAndPIdServiceImpl implements FindBrokerProjectByBIdAndPIdService {

    @Autowired
    private BrokerProjectDao brokerProjectDao;

    @Override
    public BrokerProject find(long brokerId, long projectId) throws Exception {
        return brokerProjectDao.findByBrokerIdAndProjectId(brokerId, projectId);
    }
}
