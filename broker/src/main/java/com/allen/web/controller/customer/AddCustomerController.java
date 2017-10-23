package com.allen.web.controller.customer;

import com.alibaba.fastjson.JSONObject;
import com.allen.service.customer.AddCustomerService;
import com.allen.util.UserUtil;
import com.allen.web.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Allen on 2017/6/28.
 */
@Controller
@RequestMapping(value = "/addCustomer")
public class AddCustomerController extends BaseController {

    @Autowired
    private AddCustomerService addCustomerService;

    /**
     * @param request
     * @return
     */
    @RequestMapping(value = "addForYx")
    @ResponseBody
    public JSONObject addForYx(HttpServletRequest request, String brokerZz) throws Exception {
        JSONObject jsonObject = new JSONObject();
        addCustomerService.addForYxApp(UserUtil.getLoginUserForLoginName(request), brokerZz, 1l);
        jsonObject.put("state", 0);
        return jsonObject;
    }
}
