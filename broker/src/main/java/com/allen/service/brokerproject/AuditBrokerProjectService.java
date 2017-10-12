package com.allen.service.brokerproject;

/**
 * Created by Allen on 2017/10/11.
 */
public interface AuditBrokerProjectService {
    public void pass(long id, long loginId, String loginName)throws Exception;
    public void not(long id, String content, long loginId, String loginName)throws Exception;
}
