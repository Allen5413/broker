package com.allen.dao.recommendman;

import com.allen.entity.chief.RecommendMan;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Allen on 2017/11/29.
 */
public interface RecommendManDao extends CrudRepository<RecommendMan, Long> {
    public RecommendMan findByZz(String zz)throws Exception;
}
