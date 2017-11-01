package com.allen.service.project.impl;

import com.allen.dao.brokerproject.BrokerProjectDao;
import com.allen.dao.customer.CustomerDao;
import com.allen.dao.project.ProjectDao;
import com.allen.service.project.DelProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Allen on 2017/11/1.
 */
@Service
public class DelProjectServiceImpl implements DelProjectService {

    @Autowired
    private BrokerProjectDao brokerProjectDao;
    @Autowired
    private CustomerDao customerDao;
    @Autowired
    private ProjectDao projectDao;

    @Override
    @Transactional
    public void del(long id) throws Exception {
        customerDao.delByProjectId(id);
        brokerProjectDao.delByProjectId(id);
        projectDao.delete(id);
    }
}
