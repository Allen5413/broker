package com.allen.youxue.service.team;

import com.allen.youxue.entity.team.Team;

/**
 * Created by Allen on 2017/10/17 0017.
 */
public interface AddYxTeamService {
    public void addHead(Team team, long loginId)throws Exception;
}
