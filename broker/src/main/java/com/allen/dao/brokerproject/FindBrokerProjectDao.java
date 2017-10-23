package com.allen.dao.brokerproject;

import com.allen.dao.BaseQueryDao;
import com.allen.dao.PageInfo;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by Allen on 2017/10/10.
 */
@Service
public class FindBrokerProjectDao extends BaseQueryDao {
    public PageInfo findPage(PageInfo pageInfo, Map<String, Object> paramsMap, Map<String, Boolean> sortMap)throws Exception{
        String fields = "bp.id, bp.creator, p.name pName, bp.create_time createTime, p.id pId";
        String defaultWhere = "p.id = bp.project_id";
        String[] tableNames = {"broker_project bp", "project p"};
        return super.findPageByNativeSqlToMap(pageInfo, fields, defaultWhere, tableNames, paramsMap, sortMap);
    }
}
