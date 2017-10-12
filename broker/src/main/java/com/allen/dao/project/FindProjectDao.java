package com.allen.dao.project;

import com.allen.dao.BaseQueryDao;
import com.allen.dao.PageInfo;
import org.springframework.stereotype.Service;

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
}
