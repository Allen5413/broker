package com.allen.base.scheduled;

import com.allen.service.broker.RemoveBlackBrokerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Created by Allen on 2017/10/16 0016.
 */
@Component
public class CheckSixBlackBrokerScheduled {

    @Autowired
    private RemoveBlackBrokerService removeBlackBrokerService;

    /**
     * 凌晨0点2分执行，检查拉黑6个月的经纪人是否已经到了解禁时间
     * @throws Exception
     */
    @Scheduled(cron = "00 02 00 * * ?")
    public void check() throws Exception{
        removeBlackBrokerService.autoRemoveBlack();
    }
}
