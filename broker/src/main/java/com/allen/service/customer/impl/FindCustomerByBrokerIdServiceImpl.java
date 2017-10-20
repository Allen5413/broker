package com.allen.service.customer.impl;

import com.alibaba.fastjson.JSONObject;
import com.allen.base.exception.BusinessException;
import com.allen.dao.customer.FindCustomerDao;
import com.allen.entity.broker.Customer;
import com.allen.service.attop.AttopService;
import com.allen.service.customer.FindCustomerByBrokerIdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Allen on 2017/10/16.
 */
@Service
public class FindCustomerByBrokerIdServiceImpl implements FindCustomerByBrokerIdService {

    @Autowired
    private FindCustomerDao findCustomerDao;
    @Autowired
    private AttopService attopService;

    @Override
    public List<JSONObject> find(long brokerId, long projectId) throws Exception {
        List<JSONObject> list = new ArrayList<JSONObject>();
        List<Customer> customerList = findCustomerDao.findByBrokerAndProject(brokerId, projectId);
        if(null != customerList && 0 < customerList.size()){
            for(Customer customer : customerList){
                JSONObject json = new JSONObject();
                JSONObject attopJSON = attopService.findZzInfo(customer.getZz(), "");
                if ("0".equals(attopJSON.get("status"))) {
                    throw new BusinessException("接口获取学校信息失败！");
                }
                List list2 = (List) attopJSON.get("data");
                if(list2 != null && 0 < list2.size()){
                    JSONObject userSchool = (JSONObject) list2.get(0);
                    String name = userSchool.get("realname").toString();
                    json.put("name", name);
                    json.put("nickName", userSchool.get("nickname").toString());
                }
                json.put("endLoginTime", null == customer.getEndLoginTime() ? "" : customer.getEndLoginTime().toString());
                json.put("loginCount", customer.getLoginCount());
                list.add(json);
            }
        }
        return list;
    }
}
