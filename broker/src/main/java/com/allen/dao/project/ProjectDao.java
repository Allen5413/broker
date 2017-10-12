package com.allen.dao.project;

import com.allen.entity.project.Project;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Allen on 2017/10/10.
 */
public interface ProjectDao extends CrudRepository<Project, Long> {
    public Project findByName(String name)throws Exception;
}
