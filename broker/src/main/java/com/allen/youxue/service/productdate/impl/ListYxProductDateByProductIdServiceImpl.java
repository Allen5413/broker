package com.allen.youxue.service.productdate.impl;

import com.allen.youxue.dao.productdate.YxProductDateDao;
import com.allen.youxue.entity.product.ProductDate;
import com.allen.youxue.service.productdate.ListYxProductDateByProductIdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Allen on 2017/10/17 0017.
 */
@Service
public class ListYxProductDateByProductIdServiceImpl implements ListYxProductDateByProductIdService {

    @Autowired
    private YxProductDateDao yxProductDateDao;

    @Override
    public List<ProductDate> find(long productId) throws Exception {
        return yxProductDateDao.findByProductId(productId);
    }
}
