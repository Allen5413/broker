package com.allen.youxue.service.team.impl;

import com.allen.base.exception.BusinessException;
import com.allen.dao.customer.CustomerDao;
import com.allen.entity.broker.Customer;
import com.allen.util.DateUtil;
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
    private CustomerDao customerDao;
    @Autowired
    private YxTeamDao yxTeamDao;

    @Override
    @Transactional
    public void signUp(long projectId, String zz, String yyDate, long brokerId, long teamHeadId) throws Exception {
        Team team = yxTeamDao.findByZzAndParentId(zz, teamHeadId);
        if(null != team){
            throw new BusinessException("你已经是该团的团员，不能重复报名");
        }
        Customer customer = customerDao.findByZzAndBrokerIdAndProjectId(zz, brokerId, projectId);
        if(null == customer){
            customer = new Customer();
            customer.setBrokerId(brokerId);
            customer.setCreator(zz);
            customer.setEndLoginTime(DateUtil.getLongNowTime());
            customer.setLoginCount(1);
            customer.setProjectId(projectId);
            customer.setOperator(zz);
            customer.setZz(zz);
            customerDao.save(customer);
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
