package com.allen.service.customer.impl;

import com.allen.dao.customer.CustomerDao;
import com.allen.dao.customer.FindCustomerDao;
import com.allen.entity.broker.Customer;
import com.allen.service.customer.CheckCustomerEndDateIsOverdueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * Created by Allen on 2017/10/27.
 */
@Service
public class CheckCustomerEndDateIsOverdueServiceImpl implements CheckCustomerEndDateIsOverdueService {

    @Autowired
    private FindCustomerDao findCustomerDao;
    @Autowired
    private CustomerDao customerDao;

    @Override
    @Transactional
    public void check() throws Exception {
        List<Map> list = findCustomerDao.findEndDateIsOverdue();
        if(null != list && 0 < list.size()){
            for(Map map : list){
                long id = Long.parseLong(map.get("id").toString());
                int day = Integer.parseInt(map.get("day").toString());
                int frequency = Integer.parseInt(map.get("frequency").toString());
                if(day > frequency){
                    Customer customer = customerDao.findOne(id);
                    customer.setBrokerId(null);
                    customerDao.save(customer);
                }
            }
        }
    }
}
