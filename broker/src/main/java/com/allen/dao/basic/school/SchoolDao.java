package com.allen.dao.basic.school;

import com.allen.entity.basic.School;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Allen on 2017/11/28.
 */
public interface SchoolDao extends CrudRepository<School, Long> {
}
