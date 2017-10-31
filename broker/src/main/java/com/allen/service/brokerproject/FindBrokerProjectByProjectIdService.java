package com.allen.service.brokerproject;

import com.allen.entity.brokerproject.BrokerProject;

import java.util.List;

/**
 * Created by Allen on 2017/10/31.
 */
public interface FindBrokerProjectByProjectIdService {
    public List<BrokerProject> find(long projectId)throws Exception;

    public List<BrokerProject> findByState(long projectId, int state)throws Exception;
}
