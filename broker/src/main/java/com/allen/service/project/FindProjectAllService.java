package com.allen.service.project;

import com.allen.entity.project.Project;

import java.util.List;


/**
 * Created by Allen on 2017/10/12.
 */
public interface FindProjectAllService {
    public List<Project> find()throws Exception;
}
