package com.allen.youxue.service.team.impl;

import com.allen.youxue.dao.team.YxTeamDao;
import com.allen.youxue.entity.team.Team;
import com.allen.youxue.service.team.FindYxTeamByZzAndIsHeadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Allen on 2017/10/20.
 */
@Service
public class FindYxTeamByZzAndIsHeadServiceImpl implements FindYxTeamByZzAndIsHeadService {

    @Autowired
    private YxTeamDao yxTeamDao;

    @Override
    public List<Team> find(String zz, int isHead) throws Exception {
        return yxTeamDao.findByZzAndIsHead(zz, isHead);
    }
}
