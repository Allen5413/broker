package com.allen.service.broker.impl;

import com.allen.dao.broker.BrokerDao;
import com.allen.dao.log.LogDao;
import com.allen.entity.broker.Broker;
import com.allen.entity.log.Log;
import com.allen.service.broker.RemoveBlackBrokerService;
import com.allen.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.beans.Transient;
import java.util.Date;
import java.util.List;

/**
 * Created by Allen on 2017/10/16 0016.
 */
@Service
public class RemoveBlackBrokerServiceImpl implements RemoveBlackBrokerService {

    @Autowired
    private BrokerDao brokerDao;
    @Autowired
    private LogDao logDao;

    @Override
    @Transient
    public void removeBlack(long id, long loginId, String loginName) throws Exception {
        Broker broker = brokerDao.findOne(id);
        broker.setBlackReason(null);
        broker.setBlackTime(null);
        broker.setBlackType(null);
        broker.setIsBlack(Broker.ISBLACK_NOT);
        brokerDao.save(broker);

        Log log = new Log();
        log.setOperatorId(loginId);
        log.setOperatorName(loginName);
        log.setType(Log.TYPE_EDIT);
        log.setContent("解除经纪人<span style='color:red'>"+broker.getZz()+"</span> 的黑名单");
        logDao.save(log);
    }

    @Override
    public void autoRemoveBlack() throws Exception {
        List<Broker> list = brokerDao.findByBlackType(Broker.BLACKTYPE_SIXMONTH);
        if(null != list && 0 < list.size()){
            String nowDate = DateUtil.getShortNowTime().toString();
            for(Broker broker : list){
                if(0 < DateUtil.compareDate(nowDate, DateUtil.getAfterMonth(broker.getBlackTime(), 6))) {
                    broker.setIsBlack(Broker.ISBLACK_NOT);
                    broker.setBlackType(null);
                    broker.setBlackTime(null);
                    broker.setBlackReason(null);
                    brokerDao.save(broker);
                }
            }
        }
    }
}
