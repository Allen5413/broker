package com.allen.service.customer;

import com.allen.entity.broker.Customer;

/**
 * Created by Allen on 2017/11/8.
 */
public interface FindCustomerByZzAndProjectIdService {
    public Customer find(String zz, long projectId)throws Exception;
}
