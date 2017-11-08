package com.allen.youxue.service.teamimg.impl;

import com.allen.util.UpLoadFileUtil;
import com.allen.youxue.dao.teamimg.YxTeamImgDao;
import com.allen.youxue.entity.team.TeamImg;
import com.allen.youxue.service.teamimg.DelYxTeamImgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Allen on 2017/11/8.
 */
@Service
public class DelYxTeamImgServiceImpl implements DelYxTeamImgService {

    @Autowired
    private YxTeamImgDao yxTeamImgDao;

    @Override
    @Transactional
    public void del(HttpServletRequest request, String path, String smallPath) throws Exception {
        yxTeamImgDao.delByImgUrl(path);
        UpLoadFileUtil.delFile(request, path.substring(path.indexOf("/upload"), path.length()));
        UpLoadFileUtil.delFile(request, smallPath.substring(smallPath.indexOf("/upload"), smallPath.length()));
    }

    @Override
    @Transactional
    public void delByZz(HttpServletRequest request, String zz) throws Exception {
        List<TeamImg> teamImgList = yxTeamImgDao.findByZz(zz);
        if(null != teamImgList && 0 < teamImgList.size()){
            for (TeamImg teamImg : teamImgList){
                this.del(request, teamImg.getImgUrl(), teamImg.getImgSmallUrl());
            }
        }
    }
}
