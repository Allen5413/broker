package com.allen.dao.customer;

import com.allen.dao.BaseQueryDao;
import com.allen.entity.broker.Customer;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Allen on 2017/10/10.
 */
@Service
public class FindCustomerDao extends BaseQueryDao {
    public List<Customer> findByBrokerAndProject(Long brokerId, Long projectId)throws Exception{
        Map<String, Object> paramsMap = new HashMap<String, Object>(2);
        paramsMap.put("c.brokerId", brokerId);
        paramsMap.put("c.projectId", projectId);
        Map<String, Boolean> sortMap = new HashMap<String, Boolean>(1);
        sortMap.put("c.id", true);
        String fields = "c";
        String defaultWhere = "1=1";
        String[] tableNames = {"Customer c"};
        return super.findListByHql(tableNames, fields, defaultWhere, paramsMap, sortMap);
    }
}
