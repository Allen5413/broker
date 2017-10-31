package com.allen.service.brokerproject.impl;

import com.alibaba.fastjson.JSONObject;
import com.allen.base.exception.BusinessException;
import com.allen.dao.brokerproject.BrokerProjectDao;
import com.allen.service.attop.AttopService;
import com.allen.service.brokerproject.FindBrokerAndCustomerNumByProjectIdAndRandomService;
import com.allen.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Allen on 2017/10/31.
 */
@Service
public class FindBrokerAndCustomerNumByProjectIdAndRandomServiceImpl implements FindBrokerAndCustomerNumByProjectIdAndRandomService {

    @Autowired
    private BrokerProjectDao brokerProjectDao;
    @Autowired
    private AttopService attopService;

    @Override
    public List<JSONObject> find(long projectId, String[] indexs) throws Exception {
        List<JSONObject> returnList = new ArrayList<JSONObject>();
        if(null != indexs && 0 < indexs.length){
            JSONObject zzNumJSON = new JSONObject();
            String zzs = "";
            for(String index : indexs){
                List<Object[]> list = brokerProjectDao.findBrokerAndCustomerNumByProjectIdAndIndex(projectId, Integer.parseInt(index));
                if(null != list && 0 < list.size()){
                    if(null != list.get(0)[0] && !StringUtil.isEmpty(list.get(0)[0].toString())) {
                        String zz = list.get(0)[0].toString();
                        int num = Integer.parseInt(list.get(0)[1].toString());
                        zzNumJSON.put(zz, num);
                        zzs += zz + ",";
                    }
                }
            }
            zzs = zzs.substring(0, zzs.length()-1);
            JSONObject attopJSON = attopService.findZzInfo(zzs, "");
            if ("0".equals(attopJSON.get("status"))) {
                throw new BusinessException("接口获取学校信息失败！");
            }
            List list = (List) attopJSON.get("data");
            if(list != null && 0 < list.size()){
                for(int i=0; i<list.size(); i++){
                    JSONObject userSchool = (JSONObject) list.get(i);
                    int num = null == zzNumJSON.get(userSchool.get("zz").toString()) ? 0 : Integer.parseInt(zzNumJSON.get(userSchool.get("zz").toString()).toString());
                    userSchool.put("customerNum",  num);
                    returnList.add(userSchool);
                }
            }
        }
        return returnList;
    }
}
