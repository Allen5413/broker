package com.allen.youxue.service.product.impl;

import com.allen.dao.PageInfo;
import com.allen.youxue.dao.product.FindYxProductDao;
import com.allen.youxue.service.product.PageYxProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by Allen on 2017/10/17 0017.
 */
@Service
public class PageYxProductServiceImpl implements PageYxProductService {

    @Autowired
    private FindYxProductDao findYxProductDao;

    @Override
    public PageInfo find(PageInfo pageInfo, Map<String, Object> paramsMap, Map<String, Boolean> sortMap) throws Exception {
        return findYxProductDao.findPage(pageInfo, paramsMap, sortMap);
    }
}
