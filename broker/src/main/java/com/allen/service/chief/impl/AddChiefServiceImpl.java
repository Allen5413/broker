package com.allen.service.chief.impl;

import com.allen.base.exception.BusinessException;
import com.allen.dao.chief.ChiefDao;
import com.allen.entity.chief.Chief;
import com.allen.entity.log.Log;
import com.allen.service.chief.AddChiefService;
import com.allen.service.log.AddLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Allen on 2017/11/29.
 */
@Service
public class AddChiefServiceImpl implements AddChiefService {

    @Autowired
    private ChiefDao chiefDao;
    @Autowired
    private AddLogService addLogService;

    @Override
    @Transactional
    public void add(String zz, String no, long loginId, String loginName) throws Exception {
        Chief chief = chiefDao.findBySchoolNo(no);
        if(null != chief){
            throw new BusinessException("该学校已经有校园总监了");
        }
        chief = new Chief();
        chief.setZz(zz);
        chief.setSchoolNo(no);
        chief.setCreator(loginName);
        chiefDao.save(chief);

        addLogService.add(loginId, loginName, Log.TYPE_ADD, "添加了学校No："+no+"的总监ZZ："+zz);

    }
}
