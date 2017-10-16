package com.allen.dao.customer;

import com.allen.entity.broker.Customer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.math.BigInteger;

/**
 * Created by Allen on 2017/10/16.
 */
public interface CustomerDao extends CrudRepository<Customer, Long> {

    @Query(nativeQuery = true, value = "select count(*) from customer c, customer_project cp where c.id = cp.customer_id and cp.broker_id = ?1")
    public BigInteger findByBrokerId(long brokerId)throws Exception;
}
