package com.allen.youxue.service.team;

import com.allen.youxue.entity.team.Team;

import java.util.List;

/**
 * Created by Allen on 2017/10/20.
 */
public interface FindYxTeamByZzAndIsHeadService {
    public List<Team> find(String zz, int isHead)throws Exception;
}
