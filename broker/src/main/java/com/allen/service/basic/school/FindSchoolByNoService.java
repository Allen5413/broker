package com.allen.service.basic.school;

import com.allen.entity.basic.School;

/**
 * Created by Allen on 2017/11/30.
 */
public interface FindSchoolByNoService {
    public School find(String no)throws Exception;
}
