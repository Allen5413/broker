package com.allen.youxue.web.controller.app;

import com.alibaba.fastjson.JSONObject;
import com.allen.service.broker.FindBrokerByZZService;
import com.allen.util.UserUtil;
import com.allen.web.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Allen on 2017/11/7.
 */
@Controller
@RequestMapping(value = "/youxueApp/fenXiangYx")
public class FenXiangYxController extends BaseController {

    @Autowired
    private FindBrokerByZZService findBrokerByZZService;

    /**
     * @return
     */
    @RequestMapping(value = "fenxiang")
    public String openApply(HttpServletRequest request, String brokerZz)throws Exception{
        JSONObject broker = findBrokerByZZService.findAttop(brokerZz);
        request.setAttribute("broker", broker);
        request.setAttribute("projectId", UserUtil.getLoginUserForProjectId(request));
        return "youxue/app/fenxiang";
    }
}
