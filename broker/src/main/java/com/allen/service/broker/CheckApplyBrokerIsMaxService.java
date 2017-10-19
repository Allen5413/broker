package com.allen.service.broker;

/**
 * Created by Allen on 2017/10/20 0020.
 */
public interface CheckApplyBrokerIsMaxService {
    public boolean check(long projectId, String sCode, int snum)throws Exception;
}
