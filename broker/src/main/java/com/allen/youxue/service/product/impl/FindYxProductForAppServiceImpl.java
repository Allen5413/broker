package com.allen.youxue.service.product.impl;

import com.allen.youxue.dao.product.FindYxProductDao;
import com.allen.youxue.service.product.FindYxProductForAppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by Allen on 2017/10/19.
 */
@Service
public class FindYxProductForAppServiceImpl implements FindYxProductForAppService {

    @Autowired
    private FindYxProductDao findYxProductDao;

    @Override
    public List<Map> find(long projectId) throws Exception {
        return findYxProductDao.findForApp(projectId);
    }
}
