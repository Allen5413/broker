package com.allen.service.app.customer.impl;

import com.alibaba.fastjson.JSONObject;
import com.allen.base.exception.BusinessException;
import com.allen.dao.customer.CustomerDao;
import com.allen.dao.customerdaylogincount.CustomerDayLoginCountDao;
import com.allen.entity.broker.Customer;
import com.allen.service.app.customer.FindCustomerByIdForAppService;
import com.allen.service.broker.FindBrokerByZZService;
import com.allen.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Allen on 2017/10/24.
 */
@Service
public class FindCustomerByIdForAppServiceImpl implements FindCustomerByIdForAppService {

    @Autowired
    private CustomerDao customerDao;
    @Autowired
    private CustomerDayLoginCountDao customerDayLoginCountDao;
    @Autowired
    private FindBrokerByZZService findBrokerByZZService;

    @Override
    public JSONObject find(HttpServletRequest request) throws Exception {
        JSONObject jsonObject = new JSONObject();
        String id = request.getParameter("id");
        if(StringUtil.isEmpty(id)){
            throw new BusinessException("没有传入成员id");
        }
        Customer customer = customerDao.findOne(Long.parseLong(id));
        if(null == customer){
            throw new BusinessException("没有找到成员信息");
        }
        JSONObject attopJSON = findBrokerByZZService.findAttop(customer.getZz());
        jsonObject.put("zz", customer.getZz());
        jsonObject.put("icon", attopJSON.get("icon"));
        jsonObject.put("qq", attopJSON.get("qq"));
        jsonObject.put("nickName", attopJSON.get("nickname"));
        jsonObject.put("sName", attopJSON.get("sname"));
        jsonObject.put("mobile", attopJSON.get("mobile"));
        jsonObject.put("isStar", customer.getIsStar());
        jsonObject.put("remark", StringUtil.isEmpty(customer.getRemark()) ? "" : customer.getRemark());

        //查询7天登录次数统计
        List<Object[]> list = customerDayLoginCountDao.find7DayByZzAndProjectId(customer.getZz(), customer.getProjectId());
        List<JSONObject> loginCountList = new ArrayList<JSONObject>();
        if(null != list && 0 < list.size()){
            for(Object[] objs : list){
                JSONObject json = new JSONObject();
                json.put("date", objs[0]);
                json.put("num", objs[1]);
                loginCountList.add(json);
            }
        }
        jsonObject.put("loginCountList", loginCountList);
        jsonObject.put("status", 1);
        return jsonObject;
    }
}
