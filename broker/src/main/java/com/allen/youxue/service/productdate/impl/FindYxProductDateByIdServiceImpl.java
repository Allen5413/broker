package com.allen.youxue.service.productdate.impl;

import com.allen.youxue.dao.productdate.YxProductDateDao;
import com.allen.youxue.entity.product.ProductDate;
import com.allen.youxue.service.productdate.FindYxProductDateByIdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Allen on 2017/10/19 0019.
 */
@Service
public class FindYxProductDateByIdServiceImpl implements FindYxProductDateByIdService {

    @Autowired
    private YxProductDateDao yxProductDateDao;

    @Override
    public ProductDate find(long id) throws Exception {
        return yxProductDateDao.findOne(id);
    }
}
