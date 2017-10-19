package com.allen.youxue.web.controller.app;

import com.alibaba.fastjson.JSONObject;
import com.allen.entity.broker.Customer;
import com.allen.service.broker.FindBrokerByZZService;
import com.allen.service.broker.RecommendBrokerService;
import com.allen.service.customer.FindCustomerByZzAndProjectIdHaveBrokerService;
import com.allen.service.project.EditProjectVisitCountService;
import com.allen.service.project.FindProjectByIdService;
import com.allen.web.controller.BaseController;
import com.allen.youxue.service.team.FindYxTeamHeadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Allen on 2017/10/17.
 */
@Controller
@RequestMapping(value = "/youxueApp/index")
public class YouXueIndexController extends BaseController {

    @Autowired
    private FindProjectByIdService findProjectByIdService;
    @Autowired
    private EditProjectVisitCountService editProjectVisitCountService;
    @Autowired
    private FindYxTeamHeadService findYxTeamHeadService;
    @Autowired
    private FindCustomerByZzAndProjectIdHaveBrokerService findCustomerByZzAndProjectIdHaveBrokerService;
    @Autowired
    private RecommendBrokerService recommendBrokerService;
    @Autowired
    private FindBrokerByZZService findBrokerByZZService;

    @RequestMapping(value = "open")
    public String find(HttpServletRequest request, String zz) throws Exception {
        List<Customer> customerList = findCustomerByZzAndProjectIdHaveBrokerService.find(zz, 1l);
        boolean isHaveBroker = null != customerList && 0 < customerList.size() ? true : false;
        editProjectVisitCountService.edit(1l, 1);
        request.setAttribute("teamHeadCount", findYxTeamHeadService.countNum());
        request.setAttribute("visitCount", findProjectByIdService.find(1l).getVisitCount());
        request.setAttribute("isHaveBroker", isHaveBroker);
        if(!isHaveBroker){
            JSONObject json = findBrokerByZZService.findAttop(zz);
            String scode = null == json || null == json.get("scode") ? "" : json.get("scode").toString();
            request.setAttribute("brokerList", recommendBrokerService.find(scode, 1l));
        }
        request.getSession().setAttribute("loginName", zz);
        return "/youxue/app/index";
    }
}
