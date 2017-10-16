package com.allen.service.brokerproject.impl;

import com.allen.dao.broker.BrokerDao;
import com.allen.dao.brokerproject.BrokerProjectDao;
import com.allen.dao.customer.CustomerDao;
import com.allen.dao.log.LogDao;
import com.allen.dao.project.ProjectDao;
import com.allen.entity.broker.Broker;
import com.allen.entity.brokerproject.BrokerProject;
import com.allen.entity.log.Log;
import com.allen.entity.project.Project;
import com.allen.service.attop.AttopService;
import com.allen.service.brokerproject.BlackBrokerProjectService;
import com.allen.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Allen on 2017/10/16.
 */
@Service
public class BlackBrokerProjectServiceImpl implements BlackBrokerProjectService {

    @Autowired
    private BrokerProjectDao brokerProjectDao;
    @Autowired
    private LogDao logDao;
    @Autowired
    private BrokerDao brokerDao;
    @Autowired
    private ProjectDao projectDao;
    @Autowired
    private CustomerDao customerDao;
    @Autowired
    private AttopService attopService;

    @Override
    @Transactional
    public void cancel(long bpId, long loginId, String loginName) throws Exception {
        BrokerProject brokerProject = brokerProjectDao.findOne(bpId);
        long brokerId = brokerProject.getBrokerId();
        long projectId = brokerProject.getProjectId();

        //取消经纪人该项目关联
        brokerProjectDao.delete(bpId);
        //取消经纪人该项目的关联客户
        customerDao.cancelBroker(brokerId, projectId);

        Broker broker = brokerDao.findOne(brokerId);
        Project project = projectDao.findOne(projectId);
        //记录操作日志
        Log log = new Log();
        log.setOperatorId(loginId);
        log.setOperatorName(loginName);
        log.setType(Log.TYPE_EDIT);
        log.setContent("取消经纪人"+broker.getZz()+"的<span style='color:red'>"+project.getName()+"</span>的项目");
        logDao.save(log);
    }

    @Override
    @Transactional
    public void sixMonth(long bpId, long loginId, String loginName) throws Exception {
        BrokerProject brokerProject = brokerProjectDao.findOne(bpId);
        long brokerId = brokerProject.getBrokerId();
        long projectId = brokerProject.getProjectId();

        //取消经纪人该项目关联
        brokerProjectDao.delete(bpId);
        //取消经纪人该项目的关联客户
        customerDao.cancelBroker(brokerId, projectId);

        Broker broker = brokerDao.findOne(brokerId);
        Project project = projectDao.findOne(projectId);
        //改变经纪人拉黑状态
        broker.setIsBlack(Broker.ISBLACK_YES);
        broker.setBlackType(Broker.BLACKTYPE_SIXMONTH);
        broker.setBlackTime(DateUtil.getLongNowTime());
        brokerDao.save(broker);
        //记录操作日志
        Log log = new Log();
        log.setOperatorId(loginId);
        log.setOperatorName(loginName);
        log.setType(Log.TYPE_EDIT);
        log.setContent("拉黑6个月经纪人"+broker.getZz()+"的<span style='color:red'>"+project.getName()+"</span>的项目");
        logDao.save(log);
    }

    @Override
    @Transactional
    public void forever(long bpId, String reason, long loginId, String loginName) throws Exception {
        BrokerProject brokerProject = brokerProjectDao.findOne(bpId);
        long brokerId = brokerProject.getBrokerId();

        //取消经纪人的所有关联项目
        brokerProjectDao.delByBrokerId(brokerId);
        //取消经纪人所有项目的关联客户
        customerDao.cancelBroker(brokerId);

        Broker broker = brokerDao.findOne(brokerId);
        //改变经纪人拉黑状态
        broker.setIsBlack(Broker.ISBLACK_YES);
        broker.setBlackType(Broker.BLACKTYPE_FOREVER);
        broker.setBlackTime(DateUtil.getLongNowTime());
        broker.setBlackReason(reason);
        brokerDao.save(broker);
        //记录操作日志
        Log log = new Log();
        log.setOperatorId(loginId);
        log.setOperatorName(loginName);
        log.setType(Log.TYPE_EDIT);
        log.setContent("永久拉黑经纪人"+broker.getZz());
        logDao.save(log);
        //发送系统消息
        attopService.sendMsg(broker.getZz(), "你已经被管理员永久拉黑，原因："+reason);
    }
}
