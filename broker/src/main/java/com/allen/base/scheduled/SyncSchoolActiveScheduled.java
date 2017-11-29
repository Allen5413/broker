package com.allen.base.scheduled;

import com.allen.service.basic.schoolactive.AddSchoolActiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Created by Allen on 2017/11/29.
 */
@Component
public class SyncSchoolActiveScheduled {

    @Autowired
    private AddSchoolActiveService addSchoolActiveService;

    /**
     * 晚上23点50分执行，同步各个学校的当月活跃人数
     * @throws Exception
     */
    @Scheduled(cron = "00 50 23 * * ?")
    public void check() throws Exception{
        addSchoolActiveService.add();
    }
}
