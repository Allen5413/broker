package com.allen.service.recommendman.impl;

import com.allen.base.exception.BusinessException;
import com.allen.dao.chief.ChiefDao;
import com.allen.dao.recommendman.RecommendManDao;
import com.allen.entity.chief.Chief;
import com.allen.entity.chief.RecommendMan;
import com.allen.entity.log.Log;
import com.allen.service.log.AddLogService;
import com.allen.service.recommendman.AddRecommendManService;
import com.allen.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Allen on 2017/11/29.
 */
@Service
public class AddRecommendManServiceImpl implements AddRecommendManService {

    @Autowired
    private RecommendManDao recommendManDao;
    @Autowired
    private ChiefDao chiefDao;
    @Autowired
    private AddLogService addLogService;

    @Override
    @Transactional
    public void add(String zz, String no, long loginId, String loginName) throws Exception {
        //查询校园总监
        Chief chief = chiefDao.findBySchoolNo(no);
        if(null == chief || StringUtil.isEmpty(chief.getZz())){
            throw new BusinessException("该学校还没有校园总监，请先添加校园总监");
        }
        if(!StringUtil.isEmpty(chief.getRecommendZz())){
            throw new BusinessException("该校园总监已经有推荐人，请先撤销推荐人再进行添加");
        }
        chief.setRecommendZz(zz);
        chiefDao.save(chief);

        //查询该zz是否已经成为推荐人，不是的话添加到推荐人表里面
        RecommendMan recommendMan = recommendManDao.findByZz(zz);
        if(null == recommendMan){
            recommendMan = new RecommendMan();
            recommendMan.setZz(zz);
            recommendMan.setCreator(loginName);
            recommendManDao.save(recommendMan);
        }

        //记录操作日志
        addLogService.add(loginId, loginName, Log.TYPE_ADD, "添加了ZZ："+zz+"为学校No："+no+"的推荐人");
    }
}
