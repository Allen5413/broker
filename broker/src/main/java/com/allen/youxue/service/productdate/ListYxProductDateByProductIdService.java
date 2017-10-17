package com.allen.youxue.service.productdate;

import com.allen.youxue.entity.product.ProductDate;

import java.util.List;

/**
 * Created by Allen on 2017/10/17 0017.
 */
public interface ListYxProductDateByProductIdService {
    public List<ProductDate> find(long productId)throws Exception;
}
