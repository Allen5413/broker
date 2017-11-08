package com.allen.youxue.service.teamimg.impl;

import com.allen.util.UpLoadFileUtil;
import com.allen.youxue.dao.teamimg.YxTeamImgDao;
import com.allen.youxue.service.teamimg.DelYxTeamImgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;

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
}
