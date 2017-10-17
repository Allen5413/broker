package com.allen.youxue.service.product.impl;

import com.allen.youxue.dao.product.YxProductDao;
import com.allen.youxue.entity.product.Product;
import com.allen.youxue.service.product.FindYxProductAllService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Allen on 2017/10/17 0017.
 */
@Service
public class FindYxProductAllServiceImpl implements FindYxProductAllService {

    @Autowired
    private YxProductDao yxProductDao;

    @Override
    public List<Product> find() throws Exception {
        return (List<Product>) yxProductDao.findAll();
    }
}
