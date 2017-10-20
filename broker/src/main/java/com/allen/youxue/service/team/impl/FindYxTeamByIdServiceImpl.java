package com.allen.youxue.service.team.impl;

import com.alibaba.fastjson.JSONObject;
import com.allen.base.exception.BusinessException;
import com.allen.service.attop.AttopService;
import com.allen.youxue.dao.team.YxTeamDao;
import com.allen.youxue.entity.team.Team;
import com.allen.youxue.service.team.FindYxTeamByIdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Allen on 2017/10/18.
 */
@Service
public class FindYxTeamByIdServiceImpl implements FindYxTeamByIdService {

    @Autowired
    private YxTeamDao yxTeamDao;
    @Autowired
    private AttopService attopService;

    @Override
    public Team find(long id) throws Exception {
        return yxTeamDao.findOne(id);
    }

    @Override
    public JSONObject findAttop(long id) throws Exception {
        Team team = this.find(id);
        JSONObject json = attopService.findZzInfo(team.getZz(), "");
        if ("0".equals(json.get("status"))) {
            throw new BusinessException("接口获取学校信息失败！");
        }
        List list = (List) json.get("data");
        if(list != null && 0 < list.size()){
            for(int i=0; i<list.size(); i++){
                JSONObject userSchool = (JSONObject) list.get(i);
                userSchool.put("productDateId", team.getProductDateId());
                userSchool.put("label", team.getLabel());
                userSchool.put("id", team.getId());
                userSchool.put("teamQq", team.getQq());
                return userSchool;
            }
        }
        return null;
    }
}
