package com.allen.youxue.service.product.impl;

import com.allen.youxue.dao.product.YxProductDao;
import com.allen.youxue.entity.product.Product;
import com.allen.youxue.service.product.FindYxProductByIdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Allen on 2017/10/18.
 */
@Service
public class FindYxProductByIdServiceImpl implements FindYxProductByIdService {

    @Autowired
    private YxProductDao yxProductDao;

    @Override
    public Product find(long id) throws Exception {
        return yxProductDao.findOne(id);
    }
}
