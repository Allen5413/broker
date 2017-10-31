package com.allen.service.app.broker.impl;

import com.alibaba.fastjson.JSONObject;
import com.allen.base.exception.BusinessException;
import com.allen.entity.project.Project;
import com.allen.service.app.broker.FindBrokerNumBySchoolCodeForAppService;
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
        if(StringUtil.isEmpty(sCode)){
            throw new BusinessException("没有传入学校code");
        }
        if(StringUtil.isEmpty(sNum)){
            throw new BusinessException("没有传入学校在校生人数");
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

        //计算学校最大申请人数
        int maxNum = (int)Math.rint(Integer.parseInt(sNum) * project.getRatio() / 100);
        //查询学校当前已经申请人数
        List<JSONObject> list = findBrokerProjectBySchoolCodeService.find(Long.parseLong(projectId), sCode);
        int nowNum = null == list ? 0 : list.size();
        jsonObject.put("maxNum", maxNum);
        jsonObject.put("num", nowNum);
        jsonObject.put("status", 1);
        return jsonObject;
    }
}
