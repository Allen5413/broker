package com.allen.youxue.web.controller.app;

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
 * Created by Allen on 2017/10/17 0017.
 */
@Controller
@RequestMapping(value = "/youxueApp/addMyBroker")
public class AddYxMyBrokerController extends BaseController {

    @Autowired
    private AddCustomerService addCustomerService;

    /**
     * @param request
     * @return
     */
    @RequestMapping(value = "add")
    @ResponseBody
    public JSONObject add(HttpServletRequest request, String brokerZz) throws Exception {
        JSONObject jsonObject = new JSONObject();
        addCustomerService.addForYxApp(UserUtil.getLoginUserForLoginName(request), brokerZz, UserUtil.getLoginUserForProjectId(request));
        jsonObject.put("state", 0);
        return jsonObject;
    }
}
