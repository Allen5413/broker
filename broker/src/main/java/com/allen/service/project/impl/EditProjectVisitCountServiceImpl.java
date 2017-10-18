package com.allen.service.project.impl;

import com.allen.dao.project.ProjectDao;
import com.allen.entity.project.Project;
import com.allen.service.project.EditProjectVisitCountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Allen on 2017/10/18.
 */
@Service
public class EditProjectVisitCountServiceImpl implements EditProjectVisitCountService {

    @Autowired
    private ProjectDao projectDao;

    @Override
    public void edit(long projectId, int num) throws Exception {
        Project project = projectDao.findOne(projectId);
        project.setVisitCount(project.getVisitCount()+1);
        projectDao.save(project);
    }
}
