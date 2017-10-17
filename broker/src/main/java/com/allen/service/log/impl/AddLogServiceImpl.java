package com.allen.service.log.impl;

import com.allen.dao.log.LogDao;
import com.allen.entity.log.Log;
import com.allen.service.log.AddLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Allen on 2017/10/17 0017.
 */
@Service
public class AddLogServiceImpl implements AddLogService {

    @Autowired
    private LogDao logDao;

    @Override
    public void add(long loginId, String loginName, int type, String content) throws Exception {
        //添加操作日志记录
        Log log = new Log();
        log.setOperatorId(loginId);
        log.setOperatorName(loginName);
        log.setType(type);
        log.setContent(content);
        logDao.save(log);
    }
}
