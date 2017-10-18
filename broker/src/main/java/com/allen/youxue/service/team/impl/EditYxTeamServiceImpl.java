package com.allen.youxue.service.team.impl;

import com.allen.entity.log.Log;
import com.allen.service.log.AddLogService;
import com.allen.util.DateUtil;
import com.allen.youxue.dao.team.YxTeamDao;
import com.allen.youxue.entity.team.Team;
import com.allen.youxue.service.team.EditYxTeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Allen on 2017/10/18.
 */
@Service
public class EditYxTeamServiceImpl implements EditYxTeamService {

    @Autowired
    private YxTeamDao yxTeamDao;
    @Autowired
    private AddLogService addLogService;

    @Override
    @Transactional
    public void editState(long id, int state, String remark, long loginId, String loginName) throws Exception {
        Team team = yxTeamDao.findOne(id);
        int oldState = team.getState();
        if(null != team){
            team.setState(state);
            team.setRemark(remark);
            team.setOperator(loginName);
            team.setOperateTime(DateUtil.getLongNowTime());
            yxTeamDao.save(team);
            //记录操作日志
            addLogService.add(loginId, loginName, Log.TYPE_EDIT, "修改了团员"+team.getZz()+"的状态从<span style='color:red'>"+this.getStateName(oldState)+"</span>修改为<span style='color:red'>"+this.getStateName(state)+"</span>");
        }
    }

    private String getStateName(int state){
        switch (state) {
            case Team.STATE_SIGN:
                return "已报名";
            case Team.STATE_WAIT:
                return "待审核";
            case Team.STATE_PASS:
                return "审核通过";
            case Team.STATE_NOT:
                return "审核未通过";
            case Team.STATE_FEE:
                return "已缴费";
            default:
                return "未知";
        }
    }
}
