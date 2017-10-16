package com.allen.web.controller.broker;

import com.allen.dao.PageInfo;
import com.allen.entity.broker.Broker;
import com.allen.entity.brokerproject.BrokerProject;
import com.allen.service.broker.PageBrokerService;
import com.allen.service.brokerproject.PageBrokerProjectService;
import com.allen.web.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Allen on 2017/6/27.
 */
@Controller
@RequestMapping(value = "/pageBroker")
public class PageBrokerController extends BaseController {

    @Autowired
    private PageBrokerService pageBrokerService;

    @RequestMapping(value = "pageBlack")
    public String find(HttpServletRequest request) throws Exception {
        PageInfo pageInfo = super.getPageInfo(request);
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("b.isBlack", Broker.ISBLACK_YES);
        Map<String, Boolean> sortMap = new HashMap<String, Boolean>();
        sortMap.put("b.id", false);
        pageInfo = pageBrokerService.find(pageInfo, params, sortMap);
        request.setAttribute("pageInfo", pageInfo);
        request.setAttribute("reqParams", super.getParameters(request));
        return "/broker/pageBlack";
    }
}
