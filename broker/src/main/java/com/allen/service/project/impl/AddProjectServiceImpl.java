package com.allen.service.project.impl;

import com.allen.base.exception.BusinessException;
import com.allen.dao.log.LogDao;
import com.allen.dao.project.ProjectDao;
import com.allen.entity.log.Log;
import com.allen.entity.project.Project;
import com.allen.service.project.AddProjectService;
import com.allen.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Allen on 2017/10/10.
 */
@Service
public class AddProjectServiceImpl implements AddProjectService {

    @Autowired
    private ProjectDao projectDao;
    @Autowired
    private LogDao logDao;

    @Override
    @Transactional
    public void add(Project project, long loginId) throws Exception {
        Project project2 = projectDao.findByName(project.getName());
        if(null != project2 && !StringUtil.isEmpty(project2.getName())){
            throw new BusinessException("名称已存在！");
        }
        projectDao.save(project);

        //添加操作日志记录
        Log log = new Log();
        log.setOperatorId(loginId);
        log.setOperatorName(project.getCreator());
        log.setType(Log.TYPE_ADD);
        log.setContent("新增了名称为：<span style='color:red'>"+project.getName()+"</span> 的项目");
        logDao.save(log);
    }
}
