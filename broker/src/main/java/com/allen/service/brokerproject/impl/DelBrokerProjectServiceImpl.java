package com.allen.service.brokerproject.impl;

import com.allen.dao.broker.BrokerDao;
import com.allen.dao.brokerproject.BrokerProjectDao;
import com.allen.dao.customer.CustomerDao;
import com.allen.entity.broker.Broker;
import com.allen.service.brokerproject.DelBrokerProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Allen on 2017/11/2.
 */
@Service
public class DelBrokerProjectServiceImpl implements DelBrokerProjectService {

    @Autowired
    private BrokerProjectDao brokerProjectDao;
    @Autowired
    private BrokerDao brokerDao;
    @Autowired
    private CustomerDao customerDao;

    @Override
    @Transactional
    public void delByProjectIdAndZz(long projectId, String zz) throws Exception {
        Broker broker = brokerDao.findByZz(zz);
        brokerProjectDao.delByBrokerIdAndProjectId(broker.getId(), projectId);
        //去掉该经纪人该项目的成员关联
        customerDao.editBrokerIsNullByBrokerIdAndProjectId(broker.getId(), projectId);
    }
}
