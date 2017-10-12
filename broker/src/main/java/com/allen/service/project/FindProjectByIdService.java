package com.allen.service.project;

import com.allen.entity.project.Project;

/**
 * Created by Allen on 2017/10/10.
 */
public interface FindProjectByIdService {
    public Project find(Long id)throws Exception;
}
