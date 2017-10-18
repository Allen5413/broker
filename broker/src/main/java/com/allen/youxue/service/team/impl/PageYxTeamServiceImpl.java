package com.allen.youxue.service.team.impl;

import com.alibaba.fastjson.JSONObject;
import com.allen.base.exception.BusinessException;
import com.allen.dao.PageInfo;
import com.allen.service.attop.AttopService;
import com.allen.youxue.dao.team.FindYxTeamDao;
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
    private AttopService attopService;

    @Override
    public PageInfo findPage(PageInfo pageInfo, Map<String, Object> paramsMap, Map<String, Boolean> sortMap) throws Exception {
        pageInfo = findYxTeamDao.findPage(pageInfo, paramsMap, sortMap);
        if(null != pageInfo.getPageResults() && 0 < pageInfo.getPageResults().size()){
            List<JSONObject> list = new ArrayList<JSONObject>(pageInfo.getPageResults().size());
            for(int i=0; i<pageInfo.getPageResults().size(); i++){
                Map map = (Map) pageInfo.getPageResults().get(i);
                JSONObject json = new JSONObject();
                JSONObject attopJSON = attopService.findZzInfo(map.get("zz").toString(), "");
                if ("0".equals(attopJSON.get("status"))) {
                    throw new BusinessException("接口获取学生信息失败！");
                }
                List list2 = (List) attopJSON.get("data");
                if(list2 != null && 0 < list2.size()){
                    JSONObject userSchool = (JSONObject) list2.get(0);
                    String name = userSchool.get("realname").toString();
                    json.put("name", name);
                }
                json.put("id", map.get("id"));
                json.put("zz", map.get("zz"));
                json.put("isHead", map.get("isHead"));
                json.put("state", map.get("state"));
                json.put("pName", map.get("pName"));
                list.add(json);
            }
            pageInfo.setPageResults(list);
        }
        return pageInfo;
    }
}
