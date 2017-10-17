package com.allen.youxue.dao.product;

import com.allen.youxue.entity.product.Product;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Allen on 2017/10/17 0017.
 */
public interface YxProductDao extends CrudRepository<Product, Long> {
}
