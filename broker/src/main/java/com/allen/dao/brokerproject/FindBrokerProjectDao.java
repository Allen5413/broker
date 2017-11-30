package com.allen.dao.brokerproject;

import com.allen.dao.BaseQueryDao;
import com.allen.dao.PageInfo;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
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

    public List<Map> findBySchoolNoGroupByProject(String no)throws Exception{
        String fields = "p.name, count(*) num, ROUND(p.ratio*s.num/100) maxNum, ROUND(count(*) / ROUND(p.ratio*s.num/100) * 100, 2) bl";
        String defaultWhere = "bp.project_id = p.id and bp.broker_id = b.id and b.is_black = 0";
        String[] tableNames = {"broker_project bp", "project p", "broker b", "school s"};
        String groupBy = "name";
        Map<String, Object> paramsMap = new HashMap<String, Object>(1);
        paramsMap.put("s.no", no);
        Map<String, Boolean> sortMap = new HashMap<String, Boolean>(1);
        sortMap.put("p.id", true);
        return super.findListBySqlToMap(tableNames, fields, defaultWhere, groupBy, paramsMap, sortMap);
    }
}
