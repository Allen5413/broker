package com.allen.youxue.dao.product;

import com.allen.dao.BaseQueryDao;
import com.allen.dao.PageInfo;
import org.springframework.stereotype.Service;

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
}
