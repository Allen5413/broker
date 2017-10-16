package com.allen.service.brokerproject;

/**
 * Created by Allen on 2017/10/16.
 */
public interface BlackBrokerProjectService {
    public void cancel(long bpId, long loginId, String loginName)throws Exception;
    public void sixMonth(long bpId, long loginId, String loginName)throws Exception;
    public void forever(long bpId, String reason, long loginId, String loginName)throws Exception;
}
