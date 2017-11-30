package com.allen.service.basic.school.impl;

import com.allen.dao.basic.school.SchoolDao;
import com.allen.entity.basic.School;
import com.allen.service.basic.school.FindSchoolByNoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Allen on 2017/11/30.
 */
@Service
public class FindSchoolByNoServiceImpl implements FindSchoolByNoService {

    @Autowired
    private SchoolDao schoolDao;

    @Override
    public School find(String no) throws Exception {
        return schoolDao.findByNo(no);
    }
}
