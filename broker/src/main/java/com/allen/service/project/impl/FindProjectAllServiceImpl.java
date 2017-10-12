package com.allen.service.project.impl;

import com.allen.dao.project.ProjectDao;
import com.allen.entity.project.Project;
import com.allen.service.project.FindProjectAllService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Allen on 2017/10/12.
 */
@Service
public class FindProjectAllServiceImpl implements FindProjectAllService {

    @Autowired
    private ProjectDao projectDao;

    @Override
    public List<Project> find() throws Exception {
        return (List<Project>) projectDao.findAll();
    }
}
