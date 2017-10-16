package com.allen.web.controller.brokerproject;

import com.alibaba.fastjson.JSONObject;
import com.allen.base.exception.BusinessException;
import com.allen.entity.broker.Broker;
import com.allen.entity.brokerproject.BrokerProject;
import com.allen.service.broker.FindBrokerByZZService;
import com.allen.service.brokerproject.BlackBrokerProjectService;
import com.allen.service.brokerproject.FindBrokerProjectByBIdAndPIdService;
import com.allen.util.UserUtil;
import com.allen.web.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Allen on 2017/10/16.
 */
@Controller
@RequestMapping(value = "/blackBrokerProject")
public class BlackBrokerProjectController  extends BaseController {

    @Autowired
    private BlackBrokerProjectService blackBrokerProjectService;
    @Autowired
    private FindBrokerByZZService findBrokerByZZService;
    @Autowired
    private FindBrokerProjectByBIdAndPIdService findBrokerProjectByBIdAndPIdService;

    @RequestMapping(value = "open")
    public String open() throws Exception {
        return "/brokerproject/black";
    }

    @RequestMapping(value = "cancel")
    @ResponseBody
    public JSONObject cancel(HttpServletRequest request, String zz, long projectId) throws Exception {
        JSONObject jsonObject = new JSONObject();
        Broker broker = findBrokerByZZService.find(zz);
        if(null == broker){
            throw new BusinessException("ZZ号："+zz+", 不是经纪人");
        }
        BrokerProject brokerProject = findBrokerProjectByBIdAndPIdService.find(broker.getId(), projectId);
        blackBrokerProjectService.cancel(brokerProject.getId(), UserUtil.getLoginUserForLoginId(request), UserUtil.getLoginUserForName(request));
        jsonObject.put("state", 0);
        return jsonObject;
    }

    @RequestMapping(value = "sixMonth")
    @ResponseBody
    public JSONObject sixMonth(HttpServletRequest request, String zz, long projectId) throws Exception {
        JSONObject jsonObject = new JSONObject();
        Broker broker = findBrokerByZZService.find(zz);
        if(null == broker){
            throw new BusinessException("ZZ号："+zz+", 不是经纪人");
        }
        BrokerProject brokerProject = findBrokerProjectByBIdAndPIdService.find(broker.getId(), projectId);
        blackBrokerProjectService.sixMonth(brokerProject.getId(), UserUtil.getLoginUserForLoginId(request), UserUtil.getLoginUserForName(request));
        jsonObject.put("state", 0);
        return jsonObject;
    }

    @RequestMapping(value = "openReason")
    public String openReason(HttpServletRequest request, String zz) throws Exception {
        JSONObject json = findBrokerByZZService.findAttop(zz);
        request.setAttribute("name", json.get("realname"));
        return "/brokerproject/blackReason";
    }

    @RequestMapping(value = "forever")
    @ResponseBody
    public JSONObject forever(HttpServletRequest request, String zz, long projectId, String reason) throws Exception {
        JSONObject jsonObject = new JSONObject();
        Broker broker = findBrokerByZZService.find(zz);
        if(null == broker){
            throw new BusinessException("ZZ号："+zz+", 不是经纪人");
        }
        BrokerProject brokerProject = findBrokerProjectByBIdAndPIdService.find(broker.getId(), projectId);
        blackBrokerProjectService.forever(brokerProject.getId(), reason, UserUtil.getLoginUserForLoginId(request), UserUtil.getLoginUserForName(request));
        jsonObject.put("state", 0);
        return jsonObject;
    }
}
