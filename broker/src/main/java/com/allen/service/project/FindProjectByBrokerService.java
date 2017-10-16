package com.allen.service.project;

import java.util.List;
import java.util.Map;

/**
 * Created by Allen on 2017/10/16.
 */
public interface FindProjectByBrokerService {
    public List<Map> find(long brokerId)throws Exception;
}
