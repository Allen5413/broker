package com.allen.youxue.service.teamimg.impl;

import com.allen.youxue.dao.teamimg.FindYxTeamImgDao;
import com.allen.youxue.service.teamimg.FindYxTeamImgByZzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Allen on 2017/10/20.
 */
@Service
public class FindYxTeamImgByZzServiceImpl implements FindYxTeamImgByZzService {

    @Autowired
    private FindYxTeamImgDao findYxTeamImgDao;

    @Override
    public Map<String, List<String>> findImgByZz(String zz) throws Exception {
        List<Map> list = findYxTeamImgDao.findByZz(zz);
        Map<String, List<String>> resultMap = new LinkedHashMap<String, List<String>>();
        List<String> urlList = null;
        if(null != list && 0 < list.size()){
            for(Map map : list){
                String date = map.get("date").toString();
                String url = map.get("url").toString();

                urlList = resultMap.get(date);
                if(null == urlList){
                    urlList = new ArrayList<String>();
                }
                urlList.add(url);
                resultMap.put(date, urlList);
            }
        }
        return resultMap;
    }
}
