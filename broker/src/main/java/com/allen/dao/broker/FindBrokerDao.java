package com.allen.dao.broker;

import com.allen.dao.BaseQueryDao;
import com.allen.dao.PageInfo;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by Allen on 2017/10/10.
 */
@Service
public class FindBrokerDao extends BaseQueryDao {
    public PageInfo findPage(PageInfo pageInfo, Map<String, Object> paramsMap, Map<String, Boolean> sortMap)throws Exception{
        String fields = "b";
        String[] tableNames = {"Broker b"};
        return super.findPageByJpal(pageInfo, fields, tableNames, paramsMap, sortMap);
    }
}
