package com.allen.youxue.dao.team;

import com.allen.dao.BaseQueryDao;
import com.allen.dao.PageInfo;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by Allen on 2017/10/17 0017.
 */
@Service
public class FindYxTeamDao extends BaseQueryDao {
    public PageInfo findPage(PageInfo pageInfo, Map<String, Object> paramsMap, Map<String, Boolean> sortMap)throws Exception{
        String fields = "t.id, t.zz, t.is_head isHead, t.state, p.name pName";
        String[] tableNames = {"yx_product p", "yx_product_date pd", "yx_team t"};
        String defaultWhere = "pd.id = t.product_date_id and pd.product_id = p.id";
        return super.findPageByNativeSqlToMap(pageInfo, fields, defaultWhere, tableNames, paramsMap, sortMap);
    }
}
