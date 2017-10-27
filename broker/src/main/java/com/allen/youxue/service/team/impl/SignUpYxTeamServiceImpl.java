package com.allen.youxue.service.team.impl;

import com.alibaba.fastjson.JSONObject;
import com.allen.base.exception.BusinessException;
import com.allen.service.broker.FindBrokerByZZService;
import com.allen.youxue.dao.team.YxTeamDao;
import com.allen.youxue.entity.team.Team;
import com.allen.youxue.service.team.SignUpYxTeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Allen on 2017/10/20 0020.
 */
@Service
public class SignUpYxTeamServiceImpl implements SignUpYxTeamService {

    @Autowired
    private YxTeamDao yxTeamDao;
    @Autowired
    private FindBrokerByZZService findBrokerByZZService;

    @Override
    @Transactional
    public void signUp(long projectId, String zz, String yyDate, long teamHeadId) throws Exception {
        JSONObject json = findBrokerByZZService.findAttop(zz);
        if("0".equals(json.get("sflag").toString())){
            throw new BusinessException("您目前不是学生，请先成为学生再申请");
        }
        Team team = yxTeamDao.findByZzAndParentId(zz, teamHeadId);
        if(null != team){
            throw new BusinessException("你已经是该团的团员，不能重复报名");
        }
        Team teamHead = yxTeamDao.findOne(teamHeadId);
        team = new Team();
        team.setZz(zz);
        team.setIsHead(Team.ISHEAD_NOT);
        team.setOperator(zz);
        team.setCreator(zz);
        team.setParentId(teamHeadId);
        team.setState(Team.STATE_SIGN);
        team.setYyDate(yyDate);
        team.setProductDateId(teamHead.getProductDateId());
        yxTeamDao.save(team);
    }
}
