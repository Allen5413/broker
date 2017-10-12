package com.allen.dao.log;

import com.allen.entity.log.Log;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Allen on 2017/10/10.
 */
public interface LogDao extends CrudRepository<Log, Long> {
}
