package com.allen.youxue.service.teamimg.impl;

import com.allen.base.config.ConfigProp;
import com.allen.util.StringUtil;
import com.allen.util.UpLoadFileUtil;
import com.allen.youxue.dao.teamimg.YxTeamImgDao;
import com.allen.youxue.entity.team.TeamImg;
import com.allen.youxue.service.teamimg.AddYxTeamImgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

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
                teamImg.setIsCover(TeamImg.ISCOVER_NOT);
                teamImg.setImgUrl(domain + configProp.getUpload().get("teamImgPath") + zz + "_" + fileName);
                teamImg.setImgSmallUrl(domain+configProp.getUpload().get("teamSmallImgPath")+zz+"_"+fileName);
                teamImg.setCreator(zz);
                yxTeamImgDao.save(teamImg);
            }
        }
    }

    @Override
    public void addCover(HttpServletRequest request, String zz, String domain, String fileName) throws Exception {
        if(!StringUtil.isEmpty(fileName)) {
            List<TeamImg> teamImgList = yxTeamImgDao.findByZzAndIsCover(zz, TeamImg.ISCOVER_YES);
            TeamImg teamImg = null;
            if(null != teamImgList && 0 < teamImgList.size()){
                teamImg = teamImgList.get(0);
                //先删除掉之前的照片
                UpLoadFileUtil.delFile(request, teamImg.getImgUrl().substring(teamImg.getImgUrl().indexOf("/upload"), teamImg.getImgUrl().length()));
            }else{
                teamImg = new TeamImg();
                teamImg.setZz(zz);
                teamImg.setIsCover(TeamImg.ISCOVER_YES);
                teamImg.setCreator(zz);
            }
            teamImg.setImgUrl(domain + configProp.getUpload().get("teamCoverImgPath") + zz + "_" + fileName);
            teamImg.setImgSmallUrl(domain + configProp.getUpload().get("teamCoverImgPath") + zz + "_" + fileName);
            yxTeamImgDao.save(teamImg);
        }
    }
}
