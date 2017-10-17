package com.allen.service.log;

/**
 * Created by Allen on 2017/10/17 0017.
 */
public interface AddLogService {
    public void add(long loginId, String loginName, int type, String content)throws Exception;
}
