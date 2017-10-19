package com.allen.youxue.service.team.impl;

import com.alibaba.fastjson.JSONObject;
import com.allen.base.exception.BusinessException;
import com.allen.dao.PageInfo;
import com.allen.service.attop.AttopService;
import com.allen.youxue.dao.team.FindYxTeamDao;
import com.allen.youxue.dao.team.YxTeamDao;
import com.allen.youxue.service.team.PageYxTeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Allen on 2017/10/18.
 */
@Service
public class PageYxTeamServiceImpl implements PageYxTeamService {

    @Autowired
    private FindYxTeamDao findYxTeamDao;
    @Autowired
    private YxTeamDao yxTeamDao;
    @Autowired
    private AttopService attopService;

    @Override
    public PageInfo findPage(PageInfo pageInfo, Map<String, Object> paramsMap, Map<String, Boolean> sortMap, boolean isCountTeamNum) throws Exception {
        pageInfo = findYxTeamDao.findPage(pageInfo, paramsMap, sortMap);
        if(null != pageInfo.getPageResults() && 0 < pageInfo.getPageResults().size()){
            List<Map> list = new ArrayList<Map>(pageInfo.getPageResults().size());
            for(int i=0; i<pageInfo.getPageResults().size(); i++){
                Map map = (Map) pageInfo.getPageResults().get(i);
                JSONObject attopJSON = attopService.findZzInfo(map.get("zz").toString()+","+map.get("bZz").toString(), "");
                if ("0".equals(attopJSON.get("status"))) {
                    throw new BusinessException("接口获取学生信息失败！");
                }
                List list2 = (List) attopJSON.get("data");
                if(list2 != null && 0 < list2.size()){
                    JSONObject userSchool = (JSONObject) list2.get(0);
                    JSONObject userSchool2 = (JSONObject) list2.get(1);
                    if(map.get("zz").toString().equals(userSchool.get("zz").toString())){
                        map.put("name", userSchool.get("realname").toString());
                        map.put("sName", userSchool.get("sname").toString());
                        map.put("mobile", userSchool.get("mobile").toString());
                        map.put("icon", userSchool.get("icon").toString());
                        map.put("bName", userSchool2.get("realname").toString());
                    }
                    if(map.get("zz").toString().equals(userSchool2.get("zz").toString())){
                        map.put("name", userSchool2.get("realname").toString());
                        map.put("sName", userSchool2.get("sname").toString());
                        map.put("mobile", userSchool2.get("mobile").toString());
                        map.put("icon", userSchool2.get("icon").toString());
                        map.put("bName", userSchool.get("realname").toString());
                    }
                }
                if(isCountTeamNum){
                    //统计团员人数，只有查询条件是团长的时候，才有人数
                    map.put("teamNum", yxTeamDao.countTeamNum(Long.parseLong(map.get("id").toString())));
                }
                list.add(map);
            }
            pageInfo.setPageResults(list);
        }
        return pageInfo;
    }
}
