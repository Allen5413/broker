package com.allen.service.project.impl;

import com.allen.base.config.ConfigProp;
import com.allen.base.exception.BusinessException;
import com.allen.dao.log.LogDao;
import com.allen.dao.project.ProjectDao;
import com.allen.entity.log.Log;
import com.allen.entity.project.Project;
import com.allen.service.project.AddProjectService;
import com.allen.util.StringUtil;
import com.allen.util.UpLoadFileUtil;
import com.allen.util.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Allen on 2017/10/10.
 */
@Service
public class AddProjectServiceImpl implements AddProjectService {

    @Autowired
    private ProjectDao projectDao;
    @Autowired
    private LogDao logDao;
    @Autowired
    private ConfigProp configProp;

    @Override
    @Transactional
    public void add(Project project, HttpServletRequest request, String fileName, String domain) throws Exception {
        Project project2 = projectDao.findByName(project.getName());
        if(null != project2 && !StringUtil.isEmpty(project2.getName())){
            throw new BusinessException("名称已存在！");
        }
        projectDao.save(project);

        //添加操作日志记录
        Log log = new Log();
        log.setOperatorId(UserUtil.getLoginUserForLoginId(request));
        log.setOperatorName(project.getCreator());
        log.setType(Log.TYPE_ADD);
        log.setContent("新增了名称为：<span style='color:red'>"+project.getName()+"</span> 的项目");
        logDao.save(log);

        if(!StringUtil.isEmpty(fileName)){
            UpLoadFileUtil.custFile(request, configProp.getUpload().get("tempPath") + fileName, configProp.getUpload().get("projectPic"), project.getId() + "_" + fileName);
        }

        project.setPic(domain + configProp.getUpload().get("projectPic") + project.getId() + "_" + fileName);
        projectDao.save(project);
    }
}
