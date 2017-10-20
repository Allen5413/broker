package com.allen.youxue.service.teamimg.impl;

import com.allen.base.config.ConfigProp;
import com.allen.youxue.dao.teamimg.YxTeamImgDao;
import com.allen.youxue.entity.team.TeamImg;
import com.allen.youxue.service.teamimg.AddYxTeamImgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Allen on 2017/10/20.
 */
@Service
public class AddYxTeamImgServiceImpl implements AddYxTeamImgService {

    @Autowired
    private YxTeamImgDao yxTeamImgDao;
    @Autowired
    private ConfigProp configProp;

    @Override
    @Transactional
    public void add(String zz, String domain, String... fileNames) throws Exception {
        if(null != fileNames && 0 < fileNames.length) {
            for(String fileName : fileNames) {
                TeamImg teamImg = new TeamImg();
                teamImg.setZz(zz);
                teamImg.setImgUrl(domain+configProp.getUpload().get("teamImgPath")+zz+"_"+fileName);
                teamImg.setCreator(zz);
                yxTeamImgDao.save(teamImg);
            }
        }
    }
}
