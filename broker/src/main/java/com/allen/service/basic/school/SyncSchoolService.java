package com.allen.service.basic.school;

/**
 * Created by Allen on 2017/11/28.
 */
public interface SyncSchoolService {
    public void sync(long loginId, String loginName)throws Exception;
}
