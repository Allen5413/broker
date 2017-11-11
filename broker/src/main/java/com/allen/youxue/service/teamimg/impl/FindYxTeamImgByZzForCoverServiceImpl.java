package com.allen.youxue.service.teamimg.impl;

import com.allen.youxue.dao.teamimg.YxTeamImgDao;
import com.allen.youxue.service.teamimg.FindYxTeamImgByZzForCoverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Allen on 2017/11/11 0011.
 */
@Service
public class FindYxTeamImgByZzForCoverServiceImpl implements FindYxTeamImgByZzForCoverService {

    @Autowired
    private YxTeamImgDao yxTeamImgDao;

    @Override
    public String find(String zz) throws Exception {
        return yxTeamImgDao.findByZzForCover(zz);
    }
}
