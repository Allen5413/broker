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
        String fields = "c.id, c.creator, c.create_time createTime, c.login_count loginCount, c.operate_time operateTime, c.operator, c.zz, c.end_login_time endLoginTime, c.broker_id brokerId, c.project_id projectId, c.is_star isStar, c.remark, c.end_login_time endDate";
        String[] tableNames = {"customer c"};
        String defaultWhere = "1=1";
        return super.findPageByNativeSqlToMap(pageInfo, fields, defaultWhere, tableNames, paramsMap, sortMap);
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

    /**
     * 查询用户最后登录的那天是否已经超过项目设置的频次天数
     * @return
     * @throws Exception
     */
    public List<Map> findEndDateIsOverdue()throws Exception{
        String[] tableNames = {"customer c", "project p"};
        String fields = "c.id, TIMESTAMPDIFF(DAY, DATE_FORMAT(c.end_login_time, '%Y-%m-%d') , DATE_FORMAT(now(), '%Y-%m-%d')) day, p.frequency";
        String defaultWhere = "c.project_id = p.id";
        return super.findListBySqlToMap(tableNames, fields, defaultWhere, null, null);
    }
}
