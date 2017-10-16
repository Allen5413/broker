package com.allen.dao.customerproject;

import com.allen.entity.broker.CustomerProject;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Allen on 2017/10/16.
 */
public interface CustomerProjectDao extends CrudRepository<CustomerProject, Long> {

    @Modifying
    @Query(nativeQuery = true, value = "update customer_project set broker_id = null where broker_id = ?1")
    public void cancelBroker(long brokerId)throws Exception;

    @Modifying
    @Query(nativeQuery = true, value = "update customer_project set broker_id = null where broker_id = ?1 and project_id = ?2")
    public void cancelBroker(long brokerId, long projectId)throws Exception;
}
