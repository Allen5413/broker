package com.allen.dao.customer;

import com.allen.dao.BaseQueryDao;
import com.allen.dao.PageInfo;
import com.allen.entity.broker.Customer;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Allen on 2017/10/10.
 */
@Service
public class FindCustomerDao extends BaseQueryDao {
    public PageInfo findPage(PageInfo pageInfo, Map<String, Object> paramsMap, Map<String, Boolean> sortMap)throws Exception{
        String fields = "c";
        String[] tableNames = {"Customer c"};
        return super.findPageByJpal(pageInfo, fields, tableNames, paramsMap, sortMap);
    }

    public List<Customer> findByBrokerAndProject(Long brokerId, Long projectId)throws Exception{
        Map<String, Object> paramsMap = new HashMap<String, Object>(2);
        paramsMap.put("c.brokerId", brokerId);
        paramsMap.put("c.projectId", projectId);
        Map<String, Boolean> sortMap = new LinkedHashMap<String, Boolean>(1);
        sortMap.put("c.isStar", false);
        sortMap.put("c.id", false);
        String fields = "c";
        String defaultWhere = "1=1";
        String[] tableNames = {"Customer c"};
        return super.findListByHql(tableNames, fields, defaultWhere, paramsMap, sortMap);
    }
}
