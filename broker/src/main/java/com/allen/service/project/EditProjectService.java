package com.allen.service.project;

import com.allen.entity.project.Project;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Allen on 2017/10/10.
 */
public interface EditProjectService {
    public void edit(Project project, long loginId, String newFileName, String oldFileName, String domain, HttpServletRequest request)throws Exception;
}
