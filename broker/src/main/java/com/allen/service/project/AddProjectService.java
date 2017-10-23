package com.allen.service.project;

import com.allen.entity.project.Project;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Allen on 2017/10/10.
 */
public interface AddProjectService {
    public void add(Project project, HttpServletRequest request, String fileName, String domain)throws Exception;
}
