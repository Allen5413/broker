package com.allen.service.customerdaylogincount.impl;

import com.allen.dao.customer.CustomerDao;
import com.allen.dao.customerdaylogincount.CustomerDayLoginCountDao;
import com.allen.entity.broker.Customer;
import com.allen.entity.broker.CustomerDayLoginCount;
import com.allen.service.customerdaylogincount.AddCusotmerDayLoginCountService;
import com.allen.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Allen on 2017/10/23.
 */
@Service
public class AddCusotmerDayLoginCountServiceImpl implements AddCusotmerDayLoginCountService {

    @Autowired
    private CustomerDayLoginCountDao customerDayLoginCountDao;
    @Autowired
    private CustomerDao customerDao;

    @Override
    @Transactional
    public void add(String zz, long projectId) throws Exception {
        String date = DateUtil.getShortNowTime().toString();
        //记录每天的访问次数
        CustomerDayLoginCount customerDayLoginCount = customerDayLoginCountDao.findByZzAndProjectIdAndDate(zz, projectId, date);
        if(null == customerDayLoginCount){
            customerDayLoginCount = new CustomerDayLoginCount();
            customerDayLoginCount.setZz(zz);
            customerDayLoginCount.setDate(date);
            customerDayLoginCount.setProjectId(projectId);
            customerDayLoginCount.setCount(1);
        }else{
            customerDayLoginCount.setCount(customerDayLoginCount.getCount()+1);
        }
        customerDayLoginCountDao.save(customerDayLoginCount);

        //记录中的访问次数后最后访问时间
        List<Customer> customerList = customerDao.findByZzAndProjectId(zz, projectId);
        if(null != customerList && 0 <customerList.size()){
            for (Customer customer : customerList){
                int loginCount = customer.getLoginCount();
                customer.setLoginCount(loginCount + 1);
                customer.setEndLoginTime(DateUtil.getLongNowTime());
                customerDao.save(customer);
            }
        }
    }
}
