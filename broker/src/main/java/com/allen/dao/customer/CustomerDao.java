package com.allen.dao.customer;

import com.allen.entity.broker.Customer;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.math.BigInteger;

/**
 * Created by Allen on 2017/10/16.
 */
public interface CustomerDao extends CrudRepository<Customer, Long> {
    public Customer findByZzAndBrokerIdAndProjectId(String zz, long brokerId, long projectId)throws Exception;

    @Query(nativeQuery = true, value = "select count(*) from customer c where c.broker_id = ?1")
    public BigInteger findByBrokerId(long brokerId)throws Exception;

    @Modifying
    @Query(nativeQuery = true, value = "update customer set broker_id = null where broker_id = ?1 and project_id = ?2")
    public void cancelBroker(long brokerId, long projectId)throws Exception;

    @Modifying
    @Query(nativeQuery = true, value = "update customer set broker_id = null where broker_id = ?1")
    public void cancelBroker(long brokerId)throws Exception;
}
