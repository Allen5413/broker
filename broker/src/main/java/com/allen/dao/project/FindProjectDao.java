package com.allen.dao.project;

import com.allen.dao.BaseQueryDao;
import com.allen.dao.PageInfo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by Allen on 2017/7/4.
 */
@Service
public class FindProjectDao extends BaseQueryDao {
    public PageInfo findPage(PageInfo pageInfo, Map<String, Object> paramsMap, Map<String, Boolean> sortMap)throws Exception{
        String[] tableNames = {"Project p"};
        return super.findPageByJpal(pageInfo, tableNames, paramsMap, sortMap);
    }

    public List<Map> findByBroker(Map<String, Object> paramsMap, Map<String, Boolean> sortMap)throws Exception{
        String fields = "p.id, p.name";
        String defaultWhere = "p.id = bp.project_id";
        String[] tableNames = {"broker_project bp", "project p"};
        return super.findListBySqlToMap(tableNames, fields, defaultWhere, paramsMap, sortMap);
    }
}
