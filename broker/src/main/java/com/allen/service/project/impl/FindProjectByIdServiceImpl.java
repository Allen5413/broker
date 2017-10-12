package com.allen.service.project.impl;

import com.allen.dao.project.ProjectDao;
import com.allen.entity.project.Project;
import com.allen.service.project.FindProjectByIdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Allen on 2017/10/10.
 */
@Service
public class FindProjectByIdServiceImpl implements FindProjectByIdService {

    @Autowired
    private ProjectDao projectDao;

    @Override
    public Project find(Long id) throws Exception {
        return projectDao.findOne(id);
    }
}
