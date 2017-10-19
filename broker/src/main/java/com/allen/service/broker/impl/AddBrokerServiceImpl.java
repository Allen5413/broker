package com.allen.service.broker.impl;

import com.allen.base.exception.BusinessException;
import com.allen.dao.broker.BrokerDao;
import com.allen.dao.brokerproject.BrokerProjectDao;
import com.allen.entity.broker.Broker;
import com.allen.entity.brokerproject.BrokerProject;
import com.allen.entity.project.Project;
import com.allen.service.broker.AddBrokerService;
import com.allen.service.broker.FindBrokerByZZService;
import com.allen.service.brokerproject.FindBrokerProjectByBIdAndPIdService;
import com.allen.service.project.FindProjectByIdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Allen on 2017/10/19.
 */
@Service
public class AddBrokerServiceImpl implements AddBrokerService {

    @Autowired
    private BrokerDao brokerDao;
    @Autowired
    private BrokerProjectDao brokerProjectDao;
    @Autowired
    private FindBrokerByZZService findBrokerByZZService;
    @Autowired
    private FindBrokerProjectByBIdAndPIdService findBrokerProjectByBIdAndPIdService;
    @Autowired
    private FindProjectByIdService findProjectByIdService;

    @Override
    @Transactional
    public void add(String zz, long projectId) throws Exception {
        Project project = findProjectByIdService.find(projectId);
        if(null == project){
            throw new BusinessException("没有找到项目");
        }
        Broker broker = findBrokerByZZService.find(zz);
        BrokerProject brokerProject = null;
        if(null != broker) {
            if(Broker.ISBLACK_YES == broker.getIsBlack()){
                throw new BusinessException("你已经被管理员拉黑，目前不能再申请项目经纪人");
            }
            brokerProject = findBrokerProjectByBIdAndPIdService.find(broker.getId(), 1l);
            if(null != brokerProject){
                throw new BusinessException("你目前已是游学项目的经纪人，不用再申请");
            }
        }else{
            broker = new Broker();
            broker.setZz(zz);
            broker.setIsBlack(Broker.ISBLACK_NOT);
            broker.setCreator(zz);
            brokerDao.save(broker);
        }
        brokerProject = new BrokerProject();
        brokerProject.setProjectId(projectId);
        brokerProject.setBrokerId(broker.getId());
        if(Project.AUDITTYPE_AUTO == project.getAuditType()) {
            brokerProject.setState(BrokerProject.STATE_PASS);
        }
        if(Project.AUDITTYPE_MANUAL == project.getAuditType()) {
            brokerProject.setState(BrokerProject.STATE_WAIT);
        }
        brokerProject.setCreator(zz);
        brokerProject.setOperator(zz);
        brokerProjectDao.save(brokerProject);
    }
}
