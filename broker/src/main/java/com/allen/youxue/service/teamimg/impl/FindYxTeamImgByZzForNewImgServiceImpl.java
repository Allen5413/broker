package com.allen.youxue.service.teamimg.impl;

import com.allen.youxue.dao.teamimg.YxTeamImgDao;
import com.allen.youxue.service.teamimg.FindYxTeamImgByZzForNewImgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Allen on 2017/10/24.
 */
@Service
public class FindYxTeamImgByZzForNewImgServiceImpl implements FindYxTeamImgByZzForNewImgService {

    @Autowired
    private YxTeamImgDao yxTeamImgDao;
    @Override
    public String find(String zz) throws Exception {
        return yxTeamImgDao.findByZzForNewImg(zz);
    }
}
