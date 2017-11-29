package com.allen.service.chief.impl;

import com.allen.dao.chief.ChiefDao;
import com.allen.entity.chief.Chief;
import com.allen.entity.log.Log;
import com.allen.service.chief.DelChiefService;
import com.allen.service.log.AddLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Allen on 2017/11/29.
 */
@Service
public class DelChiefServiceImpl implements DelChiefService {

    @Autowired
    private ChiefDao chiefDao;
    @Autowired
    private AddLogService addLogService;

    @Override
    @Transactional
    public void delBySchoolNo(String no, long loginId, String loginName) throws Exception {
        Chief chief = chiefDao.findBySchoolNo(no);
        chiefDao.delete(chief);
        addLogService.add(loginId, loginName, Log.TYPE_DEL, "撤销掉学校No："+no+"的校园总监");
    }
}
