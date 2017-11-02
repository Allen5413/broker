package com.allen.service.broker.impl;

import com.alibaba.fastjson.JSONObject;
import com.allen.entity.project.Project;
import com.allen.service.broker.CheckApplyBrokerIsMaxService;
import com.allen.service.brokerproject.FindBrokerProjectBySchoolCodeService;
import com.allen.service.project.FindProjectByIdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Allen on 2017/10/20 0020.
 */
@Service
public class CheckApplyBrokerIsMaxServiceImpl implements CheckApplyBrokerIsMaxService {

    @Autowired
    private FindProjectByIdService findProjectByIdService;
    @Autowired
    private FindBrokerProjectBySchoolCodeService findBrokerProjectBySchoolCodeService;

    @Override
    public boolean check(long projectId, String sCode, int snum) throws Exception {
        Project project = findProjectByIdService.find(projectId);
        //计算学校最大申请人数
        int maxNum = (int)Math.rint(snum * project.getRatio() / 100);
        //查询学校当前已经申请人数
        List<JSONObject> list = findBrokerProjectBySchoolCodeService.findWaitAndPass(projectId, sCode);
        int nowNum = null == list ? 0 : list.size();
        if(maxNum <= nowNum){
            return true;
        }else {
            return false;
        }
    }
}
