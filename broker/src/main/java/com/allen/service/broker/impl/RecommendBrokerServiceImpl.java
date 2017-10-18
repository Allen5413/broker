package com.allen.service.broker.impl;

import com.alibaba.fastjson.JSONObject;
import com.allen.base.exception.BusinessException;
import com.allen.dao.brokerproject.BrokerProjectDao;
import com.allen.service.attop.AttopService;
import com.allen.service.broker.RecommendBrokerService;
import com.allen.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Allen on 2017/10/18 0018.
 */
@Service
public class RecommendBrokerServiceImpl implements RecommendBrokerService {

    @Autowired
    private BrokerProjectDao brokerProjectDao;
    @Autowired
    private AttopService attopService;

    @Override
    public List<JSONObject> find(String schoolCode, long projectId) throws Exception {
        //查询所有该项目的经纪人
        List<Object[]> objList = brokerProjectDao.findForSchool(projectId);
        String zzs = objList.get(0)[0].toString();
        List<JSONObject> resultList = new ArrayList<JSONObject>();
        //通过接口获取经纪人所在学校，然后匹配相同学校的
        if(null != objList && 0 < objList.size()) {
            JSONObject attopJSON = attopService.findZzInfo(zzs, "");
            if ("0".equals(attopJSON.get("status"))) {
                throw new BusinessException("接口获取学校信息失败！");
            }
            List schoolList = (List) attopJSON.get("data");
            if(schoolList != null && 0 < schoolList.size()){
                for(int i=0; i<schoolList.size(); i++){
                    JSONObject manJSON = new JSONObject();
                    JSONObject userSchool = (JSONObject) schoolList.get(i);
                    String sCode = userSchool.get("scode").toString();
                    if(!StringUtil.isEmpty(sCode) && sCode.equals(schoolCode)){
                        manJSON.put("zz", userSchool.get("zz"));
                        manJSON.put("name", userSchool.get("realname"));
                        manJSON.put("mobile", userSchool.get("mobile"));
                        manJSON.put("sName", userSchool.get("sname"));
                        manJSON.put("qq", userSchool.get("qq"));
                        manJSON.put("icon", userSchool.get("icon"));
                        resultList.add(manJSON);
                        zzs = zzs.replace(userSchool.get("zz").toString(), "");
                    }
                }
            }
        }
        //如果相同学校的经纪人不够的话，从其他学校的经纪人补位，凑齐4个推荐经纪人
        String[] zzArray = zzs.split(",");
        if(null != zzArray) {
            int surplusNum = zzArray.length < (4 - resultList.size()) ? zzs.split(",").length : 4 - resultList.size();
            zzs = "";
            int j = 0;
            for (int i = 0; i < zzArray.length; i++) {
                if(!StringUtil.isEmpty(zzArray[i])){
                    zzs += zzArray[i]+",";
                    j++;
                    if(j == surplusNum){
                        break;
                    }
                }
            }
            if(zzs.length() > 1){
                zzs = zzs.substring(0, zzs.length()-1);
                JSONObject attopJSON = attopService.findZzInfo(zzs, "");
                if ("0".equals(attopJSON.get("status"))) {
                    throw new BusinessException("接口获取学校信息失败！");
                }
                List schoolList = (List) attopJSON.get("data");
                if(schoolList != null && 0 < schoolList.size()){
                    for(int i=0; i<schoolList.size(); i++){
                        JSONObject manJSON = new JSONObject();
                        JSONObject userSchool = (JSONObject) schoolList.get(i);
                        manJSON.put("zz", userSchool.get("zz"));
                        manJSON.put("name", userSchool.get("realname"));
                        manJSON.put("mobile", userSchool.get("mobile"));
                        manJSON.put("sName", userSchool.get("sname"));
                        manJSON.put("qq", userSchool.get("qq"));
                        manJSON.put("sName", userSchool.get("sname"));
                        resultList.add(manJSON);
                    }
                }
            }
        }
        return resultList;
    }
}
