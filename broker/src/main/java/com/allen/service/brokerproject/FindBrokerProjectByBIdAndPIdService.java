package com.allen.service.brokerproject;

import com.allen.entity.brokerproject.BrokerProject;

/**
 * Created by Allen on 2017/10/16.
 */
public interface FindBrokerProjectByBIdAndPIdService {
    public BrokerProject find(long brokerId, long projectId)throws Exception;
}
