package com.allen.service.app.project.impl;

import com.alibaba.fastjson.JSONObject;
import com.allen.base.exception.BusinessException;
import com.allen.dao.project.ProjectDao;
import com.allen.entity.project.Project;
import com.allen.service.app.project.FindProjectByIdForAppService;
import com.allen.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Allen on 2017/10/23 0023.
 */
@Service
public class FindProjectByIdForAppServiceImpl implements FindProjectByIdForAppService {

    @Autowired
    private ProjectDao projectDao;

    @Override
    public JSONObject find(HttpServletRequest request) throws Exception {
        JSONObject jsonObject = new JSONObject();
        String projectId = request.getParameter("projectId");
        if(StringUtil.isEmpty(projectId)){
            throw new BusinessException("没有传入项目id");
        }
        Project project = projectDao.findOne(Long.parseLong(projectId));
        if(null != project){
            jsonObject.put("id", project.getId());
            jsonObject.put("name", project.getName());
            jsonObject.put("protocol", project.getProtocol());
        }
        jsonObject.put("status", 1);
        return jsonObject;
    }
}
