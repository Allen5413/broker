package com.allen.youxue.dao.productdate;

import com.allen.youxue.entity.product.ProductDate;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by Allen on 2017/10/17 0017.
 */
public interface YxProductDateDao extends CrudRepository<ProductDate, Long> {
    public List<ProductDate> findByProductId(long productId)throws Exception;
}
