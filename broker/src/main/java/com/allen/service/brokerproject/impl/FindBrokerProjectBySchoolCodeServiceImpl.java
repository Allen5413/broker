package com.allen.service.brokerproject.impl;

import com.alibaba.fastjson.JSONObject;
import com.allen.base.exception.BusinessException;
import com.allen.dao.brokerproject.BrokerProjectDao;
import com.allen.dao.customer.CustomerDao;
import com.allen.dao.project.ProjectDao;
import com.allen.entity.broker.Customer;
import com.allen.service.attop.AttopService;
import com.allen.service.brokerproject.FindBrokerProjectBySchoolCodeService;
import com.allen.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Allen on 2017/10/12.
 */
@Service
public class FindBrokerProjectBySchoolCodeServiceImpl implements FindBrokerProjectBySchoolCodeService {

    @Autowired
    private BrokerProjectDao brokerProjectDao;
    @Autowired
    private AttopService attopService;
    @Autowired
    private CustomerDao customerDao;
    @Autowired
    private ProjectDao projectDao;

    @Override
    public List<JSONObject> find(Long projectId, String schoolCode) throws Exception {
        List<Object[]> objList = brokerProjectDao.findForSchool(projectId);
        List<JSONObject> resultList = new ArrayList<JSONObject>();
        if(null != objList && 0 < objList.size()) {
            if(null != objList.get(0) && null != objList.get(0)[0] && !StringUtil.isEmpty(objList.get(0)[0].toString())) {
                JSONObject attopJSON = attopService.findZzInfo(objList.get(0)[0].toString(), "");
                if ("0".equals(attopJSON.get("status"))) {
                    throw new BusinessException("接口获取学校信息失败！");
                }
                List schoolList = (List) attopJSON.get("data");
                if (schoolList != null && 0 < schoolList.size()) {
                    for (int i = 0; i < schoolList.size(); i++) {
                        JSONObject manJSON = new JSONObject();
                        JSONObject userSchool = (JSONObject) schoolList.get(i);
                        String sCode = userSchool.get("scode").toString();
                        if (!StringUtil.isEmpty(sCode) && sCode.equals(schoolCode)) {
                            manJSON.put("zz", userSchool.get("zz"));
                            manJSON.put("name", userSchool.get("realname"));
                            manJSON.put("nickName", userSchool.get("nickname"));
                            manJSON.put("customerNum", customerDao.findByBrokerZzAndProjectId(userSchool.get("zz").toString(), projectId));
                            manJSON.put("minNum", projectDao.findOne(projectId).getMinNum());
                            resultList.add(manJSON);
                        }
                    }
                }
            }
        }
        return resultList;
    }
}
