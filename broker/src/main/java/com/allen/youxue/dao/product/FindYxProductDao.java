package com.allen.youxue.dao.product;

import com.allen.dao.BaseQueryDao;
import com.allen.dao.PageInfo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Allen on 2017/10/17 0017.
 */
@Service
public class FindYxProductDao extends BaseQueryDao {

    public PageInfo findPage(PageInfo pageInfo, Map<String, Object> paramsMap, Map<String, Boolean> sortMap)throws Exception{
        String fields = "p.*";
        String[] tableNames = {"yx_product p"};
        String defaultWhere = "1=1";
        return super.findPageByNativeSqlToMap(pageInfo, fields, defaultWhere, tableNames, paramsMap, sortMap);
    }

    /**
     * app上查询产品集合的方法
     * @param projectId
     * @return
     * @throws Exception
     */
    public List<Map> findForApp(long projectId)throws Exception{
        List<Object> paramsList = new ArrayList<Object>();
        String sql = "select p.id, p.name, sum(case when t.is_head = 1 then 1 else 0 end) num ";
        sql += "FROM yx_product p INNER JOIN yx_product_date pd on p.id = pd.product_id ";
        sql += "LEFT JOIN yx_team t on pd.id = t.product_date_id ";
        sql += "where p.project_id = ? ";
        paramsList.add(projectId);
        sql += "group by p.id, p.name ";
        sql += "order by p.id ";
        return super.sqlQueryByNativeSqlToMap(sql, paramsList.toArray());
    }
}
