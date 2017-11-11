package com.allen.youxue.service.team;

/**
 * Created by Allen on 2017/10/18.
 */
public interface EditYxTeamService {
    public void editState(long id, int state, String remark, long loginId, String loginName)throws Exception;

    public void editQq(long id, String qq, long loginId, String loginName)throws Exception;

    public void editVisitCount(long id)throws Exception;
}
