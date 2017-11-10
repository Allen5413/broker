package com.allen.youxue.web.controller.app;

import com.alibaba.fastjson.JSONObject;
import com.allen.base.config.ConfigProp;
import com.allen.base.exception.BusinessException;
import com.allen.dao.customer.CustomerDao;
import com.allen.entity.broker.Broker;
import com.allen.entity.broker.Customer;
import com.allen.service.attop.AttopService;
import com.allen.service.broker.FindBrokerByZZService;
import com.allen.service.customer.AddCustomerService;
import com.allen.service.customer.FindCustomerByZzAndProjectIdService;
import com.allen.util.HttpRequestUtil;
import com.allen.util.StringUtil;
import com.allen.util.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Allen on 2017/11/7.
 */
@Controller
@RequestMapping("/youxueApp")
public class LoginAppController {

    @Autowired
    private ConfigProp configProp;
    @Autowired
    private FindBrokerByZZService findBrokerByZZService;
    @Autowired
    private FindCustomerByZzAndProjectIdService findCustomerByZzAndProjectIdService;
    @Autowired
    private AddCustomerService addCustomerService;
    @Autowired
    private CustomerDao customerDao;
    @Autowired
    private AttopService attopService;

    @RequestMapping("loginApp")
    @ResponseBody
    public JSONObject userLogin(@RequestParam("loginName")String loginName,
                                @RequestParam("pwd")String pwd,
                                HttpServletRequest request)throws Exception{
        JSONObject jsonObject = new JSONObject();
        String zz = attopService.login(loginName, pwd);
        if(StringUtil.isEmpty(zz)){
            throw new BusinessException("用户名或者密码错误！");
        }
        request.getSession().setAttribute("loginName", zz);
        //是否有推荐经纪人
        String brokerZz = UserUtil.getLoginUserForBrokerZz(request);
        if(!StringUtil.isEmpty(brokerZz)){
            //检查该zz号是否是经纪人
            Broker broker = findBrokerByZZService.find(brokerZz);
            if(null != broker){
                //查询当前登录用户是否关联了经纪人，如果没关联就关联推荐经纪人
                Customer customer = findCustomerByZzAndProjectIdService.find(zz, UserUtil.getLoginUserForProjectId(request));
                if(null == customer){
                    //新增客户
                    addCustomerService.addForYxApp(zz, brokerZz, UserUtil.getLoginUserForProjectId(request));
                }else{
                    if(null == customer.getBrokerId()){
                        //关联经纪人
                        customer.setBrokerId(broker.getId());
                        customerDao.save(customer);
                    }
                }
            }
        }
        jsonObject.put("state", 0);
        return jsonObject;
    }
}
