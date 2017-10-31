package com.allen.service.brokerproject.impl;

import com.allen.dao.brokerproject.BrokerProjectDao;
import com.allen.entity.brokerproject.BrokerProject;
import com.allen.service.brokerproject.FindBrokerProjectByProjectIdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Allen on 2017/10/31.
 */
@Service
public class FindBrokerProjectByProjectIdServiceImpl implements FindBrokerProjectByProjectIdService {

    @Autowired
    private BrokerProjectDao brokerProjectDao;

    @Override
    public List<BrokerProject> find(long projectId) throws Exception {
        return brokerProjectDao.findByProjectId(projectId);
    }

    @Override
    public List<BrokerProject> findByState(long projectId, int state) throws Exception {
        return brokerProjectDao.findByProjectIdAndState(projectId, state);
    }
}
