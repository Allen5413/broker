package com.allen.youxue.service.team.impl;

import com.allen.youxue.dao.team.YxTeamDao;
import com.allen.youxue.entity.team.Team;
import com.allen.youxue.service.team.FindYxTeamByIdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Allen on 2017/10/18.
 */
@Service
public class FindYxTeamByIdServiceImpl implements FindYxTeamByIdService {

    @Autowired
    private YxTeamDao yxTeamDao;
    @Override
    public Team find(long id) throws Exception {
        return yxTeamDao.findOne(id);
    }
}
