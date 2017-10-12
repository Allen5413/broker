package com.allen.service.log.impl;

import com.allen.dao.PageInfo;
import com.allen.dao.log.FindLogDao;
import com.allen.service.log.PageLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Map;

/**
 * Created by Allen on 2017/10/10.
 */
@Service
public class PageLogServiceImpl implements PageLogService {

    @Autowired
    private FindLogDao findLogDao;

    @Override
    public PageInfo find(PageInfo pageInfo, Map<String, Object> paramsMap, Map<String, Boolean> sortMap) throws Exception {
        pageInfo = findLogDao.findPage(pageInfo, paramsMap, sortMap);
        return pageInfo;
    }
}
