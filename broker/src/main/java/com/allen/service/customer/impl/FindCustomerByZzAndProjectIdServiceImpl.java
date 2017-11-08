package com.allen.service.customer.impl;

import com.allen.dao.customer.CustomerDao;
import com.allen.entity.broker.Customer;
import com.allen.service.customer.FindCustomerByZzAndProjectIdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Allen on 2017/11/8.
 */
@Service
public class FindCustomerByZzAndProjectIdServiceImpl implements FindCustomerByZzAndProjectIdService {

    @Autowired
    private CustomerDao customerDao;

    @Override
    public Customer find(String zz, long projectId) throws Exception {
        List<Customer> customerList = customerDao.findByZzAndProjectId(zz, projectId);
        if(null != customerList && 0 < customerList.size()){
            return customerList.get(0);
        }
        return null;
    }
}
