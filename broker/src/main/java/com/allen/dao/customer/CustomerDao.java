package com.allen.dao.customer;

import com.allen.entity.broker.Customer;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.math.BigInteger;
import java.util.List;

/**
 * Created by Allen on 2017/10/16.
 */
public interface CustomerDao extends CrudRepository<Customer, Long> {
    public Customer findByZzAndBrokerIdAndProjectId(String zz, long brokerId, long projectId)throws Exception;
    public List<Customer> findByZzAndProjectId(String zz, long projectId)throws Exception;

    @Query("select c from Customer c where c.zz = ?1 and c.projectId = ?2 and c.brokerId is not null")
    public List<Customer> findByZzAndProjectIdHaveBroker(String zz, long projectId)throws Exception;

    @Query(nativeQuery = true, value = "select count(*) from customer c where c.broker_id = ?1")
    public BigInteger findByBrokerId(long brokerId)throws Exception;

    @Query(nativeQuery = true, value = "select count(*) from broker b, customer c where b.id = c.broker_id and b.zz = ?1 and c.project_id = ?2")
    public BigInteger findByBrokerZzAndProjectId(String zz, long projectId)throws Exception;

    @Modifying
    @Query(nativeQuery = true, value = "update customer set broker_id = null where broker_id = ?1 and project_id = ?2")
    public void cancelBroker(long brokerId, long projectId)throws Exception;

    @Modifying
    @Query(nativeQuery = true, value = "update customer set broker_id = null where broker_id = ?1")
    public void cancelBroker(long brokerId)throws Exception;

    /**
     * 删除一个项目下的成员
     * @param projectId
     * @throws Exception
     */
    @Modifying
    @Query(nativeQuery = true, value = "delete from customer where project_id = ?1")
    public void delByProjectId(long projectId)throws Exception;

    @Modifying
    @Query(nativeQuery = true, value = "update customer set broker_id = null where broker_id = ?1 and project_id = ?2")
    public void editBrokerIsNullByBrokerIdAndProjectId(long brokerId, long projectId)throws Exception;
}
