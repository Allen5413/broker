package com.allen.service.broker;

/**
 * Created by Allen on 2017/10/16 0016.
 */
public interface RemoveBlackBrokerService {
    public void removeBlack(long id, long loginId, String loginName)throws Exception;

    public void autoRemoveBlack()throws Exception;
}
