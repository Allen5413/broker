package com.allen.web.controller.brokerproject;

import com.alibaba.fastjson.JSONObject;
import com.allen.service.brokerproject.AuditBrokerProjectService;
import com.allen.util.UserUtil;
import com.allen.web.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Allen on 2017/6/27.
 */
@Controller
@RequestMapping(value = "/auditBrokerProject")
public class AuditBrokerProjectController extends BaseController {

    @Autowired
    private AuditBrokerProjectService auditBrokerProjectService;

    @RequestMapping(value = "pass")
    @ResponseBody
    public JSONObject pass(HttpServletRequest request, long id) throws Exception {
        JSONObject jsonObject = new JSONObject();
        auditBrokerProjectService.pass(id, UserUtil.getLoginUserForLoginId(request), UserUtil.getLoginUserForName(request));
        jsonObject.put("state", 0);
        return jsonObject;
    }

    @RequestMapping(value = "openNot")
    public String openNot() throws Exception {
        return "/brokerproject/auditReason";
    }

    @RequestMapping(value = "not")
    @ResponseBody
    public JSONObject not(HttpServletRequest request, long id, String reason) throws Exception {
        JSONObject jsonObject = new JSONObject();
        auditBrokerProjectService.not(id, reason, UserUtil.getLoginUserForLoginId(request), UserUtil.getLoginUserForName(request));
        jsonObject.put("state", 0);
        return jsonObject;
    }
}
