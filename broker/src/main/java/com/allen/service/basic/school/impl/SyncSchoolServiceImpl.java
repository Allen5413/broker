package com.allen.service.basic.school.impl;

import com.alibaba.fastjson.JSONObject;
import com.allen.dao.basic.school.SchoolDao;
import com.allen.entity.basic.School;
import com.allen.entity.log.Log;
import com.allen.service.attop.AttopService;
import com.allen.service.basic.school.SyncSchoolService;
import com.allen.service.log.AddLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Allen on 2017/11/28.
 */
@Service
public class SyncSchoolServiceImpl implements SyncSchoolService {

    @Autowired
    private AttopService attopService;
    @Autowired
    private SchoolDao schoolDao;
    @Autowired
    private AddLogService addLogService;

    @Override
    @Transactional
    public void sync(long loginId, String loginName) throws Exception {
        //先删除所有学校信息
        schoolDao.deleteAll();
        //重新添加学校信息
        List<JSONObject> list = attopService.findSchoolAll();
        if(null != list && 0 < list.size()){
            for(JSONObject json : list){
                School school = new School();
                school.setNo(null == json.get("no") ? "" : json.get("no").toString());
                school.setName(null == json.get("name") ? "" : json.get("name").toString());
                school.setType(null == json.get("type") ? "" : json.get("type").toString());
                school.setNum(null == json.get("num") ? 0 : Long.parseLong(json.get("num").toString()));
                school.setBadgeUrl(null == json.get("icon") ? "" : json.get("icon").toString());
                school.setProvCode(null == json.get("area1") ? "" : json.get("area1").toString());
                school.setCityCode(null == json.get("area2") ? "" : json.get("area2").toString());
                school.setAreaCode(null == json.get("area3") ? "" : json.get("area3").toString());
                school.setCreator(loginName);
                schoolDao.save(school);
            }
        }
        //记录操作日志
        addLogService.add(loginId, loginName, Log.TYPE_ADD, "同步了学校信息");
    }
}
