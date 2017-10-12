package com.allen.service.brokerproject.impl;

import com.allen.dao.brokerproject.BrokerProjectDao;
import com.allen.dao.log.LogDao;
import com.allen.dao.project.ProjectDao;
import com.allen.entity.brokerproject.BrokerProject;
import com.allen.entity.log.Log;
import com.allen.entity.project.Project;
import com.allen.service.attop.AttopService;
import com.allen.service.brokerproject.AuditBrokerProjectService;
import com.allen.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Allen on 2017/10/11.
 */
@Service
public class AuditBrokerProjectServiceImpl implements AuditBrokerProjectService {

    @Autowired
    private BrokerProjectDao brokerProjectDao;
    @Autowired
    private ProjectDao projectDao;
    @Autowired
    private LogDao logDao;
    @Autowired
    private AttopService attopService;

    @Override
    @Transactional
    public void pass(long id, long loginId, String loginName) throws Exception {
        BrokerProject brokerProject = brokerProjectDao.findOne(id);
        brokerProject.setState(BrokerProject.STATE_PASS);
        brokerProject.setOperator(loginName);
        brokerProject.setOperateTime(DateUtil.getLongNowTime());
        brokerProjectDao.save(brokerProject);

        Project project = projectDao.findOne(brokerProject.getProjectId());
        Log log = new Log();
        log.setOperatorId(loginId);
        log.setType(Log.TYPE_EDIT);
        log.setOperatorName(loginName);
        log.setContent("审批通过经纪人"+brokerProject.getCreator()+"申请的"+project.getName()+"项目");
        logDao.save(log);
    }

    @Override
    @Transactional
    public void not(long id, String content, long loginId, String loginName) throws Exception {
        BrokerProject brokerProject = brokerProjectDao.findOne(id);
        brokerProject.setState(BrokerProject.STATE_NOT);
        brokerProject.setReason(content);
        brokerProject.setOperator(loginName);
        brokerProject.setOperateTime(DateUtil.getLongNowTime());
        brokerProjectDao.save(brokerProject);

        Project project = projectDao.findOne(brokerProject.getProjectId());
        Log log = new Log();
        log.setOperatorId(loginId);
        log.setType(Log.TYPE_EDIT);
        log.setOperatorName(loginName);
        log.setContent("审批不通过经纪人"+brokerProject.getCreator()+"申请的"+project.getName()+"项目，原因是："+content);
        logDao.save(log);

        //发送系统消息
        attopService.sendMsg(brokerProject.getCreator(), "您申请的"+project.getName()+"项目审批没通过！原因："+content);
    }
}
