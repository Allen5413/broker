package com.allen.dao.basic.schoolactive;

import com.allen.entity.basic.SchoolActive;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Allen on 2017/11/28.
 */
public interface SchoolActiveDao extends CrudRepository<SchoolActive, Long> {
    public SchoolActive findByNoAndDate(String no, String date)throws Exception;
}
