package com.allen.service.customer.impl;

import com.allen.dao.customer.CustomerDao;
import com.allen.entity.broker.Customer;
import com.allen.service.customer.FindCustomerByZzAndProjectIdHaveBrokerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Allen on 2017/10/18 0018.
 */
@Service
public class FindCustomerByZzAndProjectIdHaveBrokerServiceImpl implements FindCustomerByZzAndProjectIdHaveBrokerService {

    @Autowired
    private CustomerDao customerDao;

    @Override
    public List<Customer> find(String zz, long projectId) throws Exception {
        return customerDao.findByZzAndProjectIdHaveBroker(zz, projectId);
    }
}
