package com.allen.youxue.service.team.impl;

import com.allen.base.exception.BusinessException;
import com.allen.dao.broker.BrokerDao;
import com.allen.dao.customer.CustomerDao;
import com.allen.entity.broker.Broker;
import com.allen.entity.broker.Customer;
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
    @Autowired
    private CustomerDao customerDao;
    @Autowired
    private BrokerDao brokerDao;

    @Override
    @Transient
    public void addHead(Team team, String brokerZz, long projectId, long loginId) throws Exception {
        Team oldTeam = yxTeamDao.findByZzAndProductDateId(team.getZz(), team.getProductDateId());
        if(null != oldTeam){
            throw new BusinessException("该ZZ号已经是该产品时间段的团长");
        }
        team.setParentId(0l);
        team.setIsHead(Team.ISHEAD_YES);
        yxTeamDao.save(team);
        //查询团长是否与经纪人有关联，如果没有关联就创建
        Broker broker = brokerDao.findByZz(brokerZz);
        if(null == broker){
            throw new BusinessException("经纪人Zz不存在！");
        }
        Customer customer = customerDao.findByZzAndBrokerIdAndProjectId(team.getZz(), broker.getId(), projectId);
        if(null == customer){
            customer = new Customer();
            customer.setZz(team.getZz());
            customer.setBrokerId(broker.getId());
            customer.setCreator(team.getCreator());
            customer.setOperator(team.getOperator());
            customer.setProjectId(projectId);
            customerDao.save(customer);
        }
        addLogService.add(loginId, team.getCreator(), Log.TYPE_ADD, "新建了"+team.getZz()+"为团长");
    }
}
