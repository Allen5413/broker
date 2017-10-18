package com.allen.youxue.service.team.impl;

import com.alibaba.fastjson.JSONObject;
import com.allen.base.exception.BusinessException;
import com.allen.service.attop.AttopService;
import com.allen.youxue.dao.team.YxTeamDao;
import com.allen.youxue.entity.team.Team;
import com.allen.youxue.service.team.FindYxTeamHeadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Allen on 2017/10/18.
 */
@Service
public class FindYxTeamHeadServiceImpl implements FindYxTeamHeadService {

    @Autowired
    private YxTeamDao yxTeamDao;
    @Autowired
    private AttopService attopService;

    @Override
    public List<JSONObject> find() throws Exception {
        List<Team> teamList = yxTeamDao.findByIsHead(Team.ISHEAD_YES);
        if(null != teamList && 0 < teamList.size()){
            List<JSONObject> list = new ArrayList<JSONObject>(teamList.size());
            for(Team team : teamList){
                JSONObject json = new JSONObject();
                json.put("id", team.getId());
                JSONObject attopJSON = attopService.findZzInfo(team.getZz(), "");
                if ("0".equals(attopJSON.get("status"))) {
                    throw new BusinessException("接口获取学生信息失败！");
                }
                List list2 = (List) attopJSON.get("data");
                if(list2 != null && 0 < list2.size()){
                    JSONObject userSchool = (JSONObject) list2.get(0);
                    json.put("name", userSchool.get("realname").toString());
                }
                list.add(json);
            }
            return list;
        }
        return null;
    }

    @Override
    public int countNum() throws Exception {
        List<Team> teamList = yxTeamDao.findByIsHead(Team.ISHEAD_YES);
        return null == teamList ? 0 : teamList.size();
    }
}
