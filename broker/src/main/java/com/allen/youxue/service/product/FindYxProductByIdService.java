package com.allen.youxue.service.product;

import com.allen.youxue.entity.product.Product;

/**
 * Created by Allen on 2017/10/18.
 */
public interface FindYxProductByIdService {
    public Product find(long id)throws Exception;
}
