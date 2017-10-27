package com.allen.base.scheduled;

import com.allen.service.customer.CheckCustomerEndDateIsOverdueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Created by Allen on 2017/10/16 0016.
 */
@Component
public class CheckCustomerEndDateIsOverdueScheduled {

    @Autowired
    private CheckCustomerEndDateIsOverdueService checkCustomerEndDateIsOverdueService;

    /**
     * 凌晨0点30分执行，检查成员的最后登录日期是否超过了项目设置的频次天数，如果超过了，就解除该项目与经纪人的关联
     * @throws Exception
     */
    @Scheduled(cron = "00 30 0 * * ?")
    public void check() throws Exception{
        checkCustomerEndDateIsOverdueService.check();
    }
}
