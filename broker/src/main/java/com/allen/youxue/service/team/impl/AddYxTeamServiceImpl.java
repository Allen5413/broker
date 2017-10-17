package com.allen.youxue.service.team.impl;

import com.allen.entity.log.Log;
import com.allen.service.log.AddLogService;
import com.allen.youxue.dao.team.YxTeamDao;
import com.allen.youxue.entity.team.Team;
import com.allen.youxue.service.team.AddYxTeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.beans.Transient;

/**
 * Created by Allen on 2017/10/17 0017.
 */
@Service
public class AddYxTeamServiceImpl implements AddYxTeamService {

    @Autowired
    private YxTeamDao yxTeamDao;
    @Autowired
    private AddLogService addLogService;

    @Override
    @Transient
    public void addHead(Team team, long loginId) throws Exception {
        team.setIsHead(Team.ISHEAD_YES);
        yxTeamDao.save(team);
        addLogService.add(loginId, team.getCreator(), Log.TYPE_ADD, "新建了"+team.getZz()+"为团长");
    }
}
