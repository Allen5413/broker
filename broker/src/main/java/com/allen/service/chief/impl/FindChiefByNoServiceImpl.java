package com.allen.service.chief.impl;

import com.allen.dao.chief.ChiefDao;
import com.allen.entity.chief.Chief;
import com.allen.service.chief.FindChiefByNoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Allen on 2017/11/30.
 */
@Service
public class FindChiefByNoServiceImpl implements FindChiefByNoService {

    @Autowired
    private ChiefDao chiefDao;

    @Override
    public Chief find(String no) throws Exception {
        return chiefDao.findBySchoolNo(no);
    }
}
