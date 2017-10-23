package com.allen.dao.customerdaylogincount;

import com.allen.entity.broker.CustomerDayLoginCount;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Allen on 2017/10/23.
 */
public interface CustomerDayLoginCountDao extends CrudRepository<CustomerDayLoginCount, Long> {
    public CustomerDayLoginCount findByZzAndProjectIdAndDate(String zz, long projectId, String date)throws Exception;
}
