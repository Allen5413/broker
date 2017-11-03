package com.allen.service.app.broker.impl;

import com.alibaba.fastjson.JSONObject;
import com.allen.base.exception.BusinessException;
import com.allen.entity.broker.Broker;
import com.allen.entity.brokerproject.BrokerProject;
import com.allen.entity.project.Project;
import com.allen.service.app.broker.FindBrokerNumBySchoolCodeForAppService;
import com.allen.service.broker.FindBrokerByZZService;
import com.allen.service.brokerproject.FindBrokerProjectByBIdAndPIdService;
import com.allen.service.brokerproject.FindBrokerProjectBySchoolCodeService;
import com.allen.service.project.FindProjectByIdService;
import com.allen.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Allen on 2017/10/24.
 */
@Service
public class FindBrokerNumBySchoolCodeForAppServiceImpl implements FindBrokerNumBySchoolCodeForAppService {

    @Autowired
    private FindProjectByIdService findProjectByIdService;
    @Autowired
    private FindBrokerProjectBySchoolCodeService findBrokerProjectBySchoolCodeService;
    @Autowired
    private FindBrokerByZZService findBrokerByZZService;
    @Autowired
    private FindBrokerProjectByBIdAndPIdService findBrokerProjectByBIdAndPIdService;

    @Override
    public JSONObject find(HttpServletRequest request) throws Exception {
        JSONObject jsonObject = new JSONObject();
        String zz = request.getParameter("zz");
        String projectId = request.getParameter("projectId");
        String sCode = request.getParameter("sCode");
        String sNum = request.getParameter("sNum");
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
        jsonObject.put("id", project.getId());
        jsonObject.put("name", StringUtil.isEmpty(project.getName()) ? "" : project.getName());
        jsonObject.put("pic", StringUtil.isEmpty(project.getPic()) ? "" : project.getPic());
        jsonObject.put("protocol", StringUtil.isEmpty(project.getProtocol()) ? "" : project.getProtocol());
        jsonObject.put("content", StringUtil.isEmpty(project.getContent()) ? "" : project.getContent());
        jsonObject.put("userState", 0);

        if(StringUtil.isEmpty(sCode)){
            jsonObject.put("status", 1);
            jsonObject.put("userState", 1);
            return jsonObject;
        }

        //查询是否已经成为了经纪人
        Broker broker = findBrokerByZZService.find(zz);
        if(null != broker) {
            jsonObject.put("status", 1);
            if(Broker.ISBLACK_YES == broker.getIsBlack()){
                jsonObject.put("userState", 5);
                return jsonObject;
            }
            BrokerProject brokerProject = findBrokerProjectByBIdAndPIdService.find(broker.getId(), Long.parseLong(projectId));
            if (null != brokerProject) {
                if(BrokerProject.STATE_PASS == brokerProject.getState()) {
                    jsonObject.put("userState", 2);
                }
                if(BrokerProject.STATE_WAIT == brokerProject.getState()) {
                    jsonObject.put("userState", 3);
                }
                if(BrokerProject.STATE_NOT == brokerProject.getState()) {
                    jsonObject.put("userState", 4);
                }
                return jsonObject;
            }
        }


        //计算学校最大申请人数
        int maxNum = 0;
        if(!StringUtil.isEmpty(sNum)){
            maxNum = (int)Math.rint(Integer.parseInt(sNum) * project.getRatio() / 100);
        }
        //查询学校当前已经申请人数
        List<JSONObject> list = findBrokerProjectBySchoolCodeService.findWaitAndPass(Long.parseLong(projectId), sCode);
        int nowNum = null == list ? 0 : list.size();
        jsonObject.put("maxNum", maxNum);
        jsonObject.put("num", nowNum);
        jsonObject.put("status", 1);
        return jsonObject;
    }
}
