package com.allen.service.brokerproject.impl;

import com.alibaba.fastjson.JSONObject;
import com.allen.base.exception.BusinessException;
import com.allen.dao.brokerproject.BrokerProjectDao;
import com.allen.service.attop.AttopService;
import com.allen.service.brokerproject.FindBrokerProjectForSchoolService;
import com.allen.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Allen on 2017/10/12.
 */
@Service
public class FindBrokerProjectForSchoolServiceImpl implements FindBrokerProjectForSchoolService {

    @Autowired
    private BrokerProjectDao brokerProjectDao;
    @Autowired
    private AttopService attopService;

    @Override
    public Map<String, JSONObject> find(Long projectId, String name) throws Exception {
        List<Object[]> objList = brokerProjectDao.findForSchool(projectId);
        Map<String, JSONObject> map = new HashMap<String, JSONObject>();
        if(null != objList && 0 < objList.size()){
            int ratio = Integer.parseInt(objList.get(0)[1].toString());
            JSONObject attopJSON = attopService.findZzInfo(objList.get(0)[0].toString(), name);
            if("0".equals(attopJSON.get("status"))){
                throw new BusinessException("接口获取学校信息失败！");
            }
            List schoolList = (List) attopJSON.get("data");
            if(schoolList != null && 0 < schoolList.size()){
                int manNum = 0;
                for(int i=0; i<schoolList.size(); i++){
                    JSONObject userSchool = (JSONObject) schoolList.get(i);
                    String sCode = userSchool.get("scode").toString();
                    if(!StringUtil.isEmpty(sCode)) {
                        String sName = userSchool.get("sname").toString();
                        String nickName = userSchool.get("nickname").toString();
                        int snum = Integer.parseInt(userSchool.get("snum").toString());

                        JSONObject school = (JSONObject) map.get(sCode);
                        if (null == school) {
                            school = new JSONObject();
                            school.put("code", sCode);
                            school.put("name", sName);
                            school.put("nickName", nickName);
                            school.put("num", snum);
                            school.put("maxNum", snum * ratio / 100);
                            school.put("manNum", 1);
                        } else {
                            manNum = Integer.parseInt(school.get(manNum).toString());
                            school.put("manNum", manNum + 1);
                        }
                        map.put(sCode, school);
                    }
                }
            }
        }
        return map;
    }
}
