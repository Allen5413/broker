package com.allen.service.basic.schoolactive.impl;

import com.allen.dao.basic.school.SchoolDao;
import com.allen.dao.basic.schoolactive.SchoolActiveDao;
import com.allen.entity.basic.School;
import com.allen.entity.basic.SchoolActive;
import com.allen.service.attop.AttopService;
import com.allen.service.basic.schoolactive.AddSchoolActiveService;
import com.allen.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by Allen on 2017/11/29.
 */
@Service
public class AddSchoolActiveServiceImpl implements AddSchoolActiveService {

    @Autowired
    private SchoolActiveDao schoolActiveDao;
    @Autowired
    private AttopService attopService;
    @Autowired
    private SchoolDao schoolDao;

    @Override
    @Transactional
    public void add() throws Exception {
        //获取当前年月
        Timestamp nowDate = DateUtil.getLongNowTime();
        String date = nowDate.toString().substring(0, 7);
        //获取有学生的高校
        List<School> schoolList = schoolDao.findForHaveNum();
        if(null != schoolList && 0 < schoolList.size()) {
            for(School school : schoolList) {
                //获取活跃人数
                int activeNum = attopService.findSchoolActiveByNo(school.getNo());
                SchoolActive schoolActive = schoolActiveDao.findByNoAndDate(school.getNo(), date);
                if (null == schoolActive) {
                    schoolActive = new SchoolActive();
                    schoolActive.setNo(school.getNo());
                    schoolActive.setDate(date);
                }
                schoolActive.setNum(activeNum);
                schoolActive.setNumPercent(new BigDecimal(activeNum*100).divide(new BigDecimal(school.getNum()), 2, BigDecimal.ROUND_HALF_UP).floatValue());
                schoolActiveDao.save(schoolActive);
                //更新学校表的当前活跃人数
                school.setActiveNum(activeNum);
                schoolDao.save(school);
            }
        }
    }
}
