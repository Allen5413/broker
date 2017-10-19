package com.allen.service.customer.impl;

import com.allen.base.exception.BusinessException;
import com.allen.dao.broker.BrokerDao;
import com.allen.dao.customer.CustomerDao;
import com.allen.entity.broker.Broker;
import com.allen.entity.broker.Customer;
import com.allen.service.customer.AddCustomerService;
import com.allen.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Allen on 2017/10/19.
 */
@Service
public class AddCustomerServiceImpl implements AddCustomerService {

    @Autowired
    private CustomerDao customerDao;
    @Autowired
    private BrokerDao brokerDao;

    @Override
    public void addForYxApp(String zz, String brokerZz, long projectId) throws Exception {
        Broker broker = brokerDao.findByZz(brokerZz);
        if(null == broker){
            throw new BusinessException("ZZ号"+brokerZz+"目前还不是我们的经纪人！");
        }
        Customer customer = new Customer();
        customer.setProjectId(projectId);
        customer.setBrokerId(broker.getId());
        customer.setZz(zz);
        customer.setCreator(zz);
        customer.setOperator(zz);
        customer.setEndLoginTime(DateUtil.getLongNowTime());
        customer.setLoginCount(1);
        customerDao.save(customer);
    }
}
