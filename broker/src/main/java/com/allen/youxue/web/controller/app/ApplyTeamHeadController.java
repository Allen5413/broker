package com.allen.youxue.web.controller.app;

import com.alibaba.fastjson.JSONObject;
import com.allen.service.broker.FindBrokerByZZService;
import com.allen.service.broker.RecommendBrokerService;
import com.allen.util.UserUtil;
import com.allen.web.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Allen on 2017/10/19.
 */
@Controller
@RequestMapping(value = "/youxueApp/applyTeamHead")
public class ApplyTeamHeadController extends BaseController {

    @Autowired
    private FindBrokerByZZService findBrokerByZZService;
    @Autowired
    private RecommendBrokerService recommendBrokerService;

    @RequestMapping(value = "open")
    public String find(HttpServletRequest request) throws Exception {
        JSONObject json = findBrokerByZZService.findAttop(UserUtil.getLoginUserForLoginName(request));
        String scode = null == json || null == json.get("scode") ? "" : json.get("scode").toString();
        request.setAttribute("brokerList", recommendBrokerService.find(scode, 1l));
        return "/youxue/app/applyTeamHead";
    }
}
