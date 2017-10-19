package com.allen.youxue.service.productdate;

import com.allen.youxue.entity.product.ProductDate;

/**
 * Created by Allen on 2017/10/19 0019.
 */
public interface FindYxProductDateByIdService {
    public ProductDate find(long id)throws Exception;
}
