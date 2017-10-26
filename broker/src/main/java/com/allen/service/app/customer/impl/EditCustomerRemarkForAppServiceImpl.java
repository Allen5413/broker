package com.allen.service.app.customer.impl;

import com.alibaba.fastjson.JSONObject;
import com.allen.base.exception.BusinessException;
import com.allen.dao.customer.CustomerDao;
import com.allen.entity.broker.Customer;
import com.allen.service.app.customer.EditCustomerRemarkForAppService;
import com.allen.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Allen on 2017/10/23 0023.
 */
@Service
public class EditCustomerRemarkForAppServiceImpl implements EditCustomerRemarkForAppService {

    @Autowired
    private CustomerDao customerDao;

    @Override
    public JSONObject edit(HttpServletRequest request)throws Exception{
        JSONObject jsonObject = new JSONObject();
        String id = request.getParameter("id");
        String remark = StringUtil.getDecode(request, "remark");
        if(StringUtil.isEmpty(id)){
            throw new BusinessException("没有传入成员id");
        }
        Customer customer = customerDao.findOne(Long.parseLong(id));
        if(null != customer){
            customer.setRemark(remark);
            customerDao.save(customer);
        }
        jsonObject.put("status", 1);
        return jsonObject;
    }
}
