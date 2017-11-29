package com.allen.service.chief.impl;

import com.alibaba.fastjson.JSONObject;
import com.allen.dao.PageInfo;
import com.allen.dao.chief.FindChiefDao;
import com.allen.service.broker.FindBrokerByZZService;
import com.allen.service.chief.PageChiefService;
import com.allen.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Allen on 2017/11/28.
 */
@Service
public class PageChiefServiceImpl implements PageChiefService {

    @Autowired
    private FindChiefDao findChiefDao;
    @Autowired
    private FindBrokerByZZService findBrokerByZZService;

    @Override
    public PageInfo find(PageInfo pageInfo, Map<String, String> paramsMap) throws Exception {
        pageInfo = findChiefDao.findPage(pageInfo, paramsMap);
        if(null != pageInfo.getPageResults() && 0 < pageInfo.getPageResults().size()){
            List<Map> list = pageInfo.getPageResults();
            List<Map> list2 = new ArrayList<Map>(list.size());
            for(Map map : list){
                String zz = null == map.get("zz") ? "" : map.get("zz").toString();
                String rZz = null == map.get("rZz") ? "" : map.get("rZz").toString();
                //查询总监姓名
                if(!StringUtil.isEmpty(zz)){
                    JSONObject json = findBrokerByZZService.findAttop(zz);
                    String name = null == json ? "" : json.get("realname").toString();
                    map.put("cName", name);
                }
                //查询推荐人姓名
                if(!StringUtil.isEmpty(rZz)){
                    JSONObject json = findBrokerByZZService.findAttop(rZz);
                    String name = null == json ? "" : json.get("realname").toString();
                    map.put("rName", name);
                }
                list2.add(map);
            }
            pageInfo.setPageResults(list2);
        }
        return pageInfo;
    }
}
