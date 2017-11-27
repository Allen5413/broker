package com.allen.service.app.broker.impl;

import com.alibaba.fastjson.JSONObject;
import com.allen.base.exception.BusinessException;
import com.allen.dao.broker.BrokerDao;
import com.allen.dao.brokerproject.BrokerProjectDao;
import com.allen.entity.broker.Broker;
import com.allen.entity.brokerproject.BrokerProject;
import com.allen.entity.project.Project;
import com.allen.service.app.broker.AddBrokerForAppService;
import com.allen.service.broker.CheckApplyBrokerIsMaxService;
import com.allen.service.broker.FindBrokerByZZService;
import com.allen.service.brokerproject.FindBrokerProjectByBIdAndPIdService;
import com.allen.service.project.FindProjectByIdService;
import com.allen.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Allen on 2017/10/19.
 */
@Service
public class AddBrokerServiceForAppImpl implements AddBrokerForAppService {

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
    @Autowired
    private CheckApplyBrokerIsMaxService checkApplyBrokerIsMaxService;

    @Override
    @Transactional
    public JSONObject add(HttpServletRequest request) throws Exception {
        JSONObject jsonObject = new JSONObject();
        String zz = request.getParameter("zz");
        String projectId = request.getParameter("projectId");
        if(StringUtil.isEmpty(zz)){
            throw new BusinessException("没有传入zz");
        }
        if(StringUtil.isEmpty(projectId)){
            throw new BusinessException("没有传入项目id");
        }
        Project project = findProjectByIdService.find(Long.parseLong(projectId));
        if(null == project){
            throw new BusinessException("没有找到项目");
        }
        //检查经纪人学校是否到达申请上限
        JSONObject json = findBrokerByZZService.findAttop(zz);
        String sCode = json.get("scode").toString();
        String mobile = json.get("mobile").toString();
        if(StringUtil.isEmpty(sCode) || StringUtil.isEmpty(mobile)){
            throw new BusinessException("请到个人中心完善手机、学校等联系方式的绑定。");
        }else {
            if (checkApplyBrokerIsMaxService.check(Long.parseLong(projectId), json.get("scode").toString(), Integer.parseInt(json.get("snum").toString()))){
                throw new BusinessException("很抱歉，每个学校经纪人名额有限，贵校本项目已不再接收新的申请。");
            }
        }
        Broker broker = findBrokerByZZService.find(zz);
        BrokerProject brokerProject = null;
        if(null != broker) {
            if(Broker.ISBLACK_YES == broker.getIsBlack()){
                throw new BusinessException("对不起，您已被取消申请新项目经纪人资格");
            }
            brokerProject = findBrokerProjectByBIdAndPIdService.find(broker.getId(), Long.parseLong(projectId));
            if(null != brokerProject){
                throw new BusinessException("您目前已是"+project.getName()+"项目的经纪人");
            }
        }else{
            broker = new Broker();
            broker.setZz(zz);
            broker.setIsBlack(Broker.ISBLACK_NOT);
            broker.setCreator(zz);
            broker.setSchoolNo(sCode);
            brokerDao.save(broker);
        }
        brokerProject = new BrokerProject();
        brokerProject.setProjectId(Long.parseLong(projectId));
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

        jsonObject.put("status", 1);
        return jsonObject;
    }
}
