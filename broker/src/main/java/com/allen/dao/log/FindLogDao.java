package com.allen.dao.log;

import com.allen.dao.BaseQueryDao;
import com.allen.dao.PageInfo;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by Allen on 2017/10/10.
 */
@Service
public class FindLogDao extends BaseQueryDao {
    public PageInfo findPage(PageInfo pageInfo, Map<String, Object> paramsMap, Map<String, Boolean> sortMap)throws Exception{
        String[] tableNames = {"Log l"};
        return super.findPageByJpal(pageInfo, tableNames, paramsMap, sortMap);
    }
}
