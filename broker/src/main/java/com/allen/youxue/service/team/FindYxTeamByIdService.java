package com.allen.youxue.service.team;

import com.alibaba.fastjson.JSONObject;
import com.allen.youxue.entity.team.Team;

/**
 * Created by Allen on 2017/10/18.
 */
public interface FindYxTeamByIdService {
    public Team find(long id)throws Exception;

    public JSONObject findAttop(long id)throws Exception;
}
