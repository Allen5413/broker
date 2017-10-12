package com.allen.service.project;

import com.allen.entity.project.Project;

/**
 * Created by Allen on 2017/10/10.
 */
public interface EditProjectService {
    public void add(Project project, long loginId)throws Exception;
}
