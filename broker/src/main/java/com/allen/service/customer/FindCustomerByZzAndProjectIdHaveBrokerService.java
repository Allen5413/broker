package com.allen.service.customer;

import com.allen.entity.broker.Customer;

import java.util.List;

/**
 * Created by Allen on 2017/10/12.
 */
public interface FindCustomerByZzAndProjectIdHaveBrokerService {
    public List<Customer> find(String zz, long projectId)throws Exception;
}
