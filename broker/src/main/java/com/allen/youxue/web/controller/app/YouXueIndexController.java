package com.allen.youxue.web.controller.app;

import com.alibaba.fastjson.JSONObject;
import com.allen.entity.broker.Customer;
import com.allen.service.broker.FindBrokerByZZService;
import com.allen.service.broker.RecommendBrokerService;
import com.allen.service.customer.FindCustomerByZzAndProjectIdHaveBrokerService;
import com.allen.service.customerdaylogincount.AddCusotmerDayLoginCountService;
import com.allen.service.project.EditProjectVisitCountService;
import com.allen.service.project.FindProjectByIdService;
import com.allen.util.StringUtil;
import com.allen.web.controller.BaseController;
import com.allen.youxue.service.product.FindYxProductForAppService;
import com.allen.youxue.service.team.FindYxTeamHeadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

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
    @Autowired
    private FindYxProductForAppService findYxProductForAppService;
    @Autowired
    private AddCusotmerDayLoginCountService addCusotmerDayLoginCountService;

    @RequestMapping(value = "open")
    public String find(HttpServletRequest request, String zz, long projectId,
                       @RequestParam(value = "notCount", required = false)String notCount) throws Exception {
        List<Customer> customerList = findCustomerByZzAndProjectIdHaveBrokerService.find(zz, projectId);
        boolean isHaveBroker = null != customerList && 0 < customerList.size() ? true : false;
        if(!StringUtil.isEmpty(notCount)) {
            //如果是app内部跳转到首页的话，就不计数
            editProjectVisitCountService.edit(projectId, 1);
            addCusotmerDayLoginCountService.add(zz, projectId);
        }
        request.setAttribute("teamHeadCount", findYxTeamHeadService.countNum());
        request.setAttribute("visitCount", findProjectByIdService.find(projectId).getVisitCount());
        request.setAttribute("isHaveBroker", isHaveBroker);
        if(!isHaveBroker){
            JSONObject json = findBrokerByZZService.findAttop(zz);
            String scode = null == json || null == json.get("scode") ? "" : json.get("scode").toString();
            request.setAttribute("brokerList", recommendBrokerService.find(scode, projectId));
        }
        List<Map> list = findYxProductForAppService.find(projectId);
        request.setAttribute("bj", list.get(0));
        request.setAttribute("xg", list.get(1));
        request.setAttribute("jq", list.get(2));
        request.setAttribute("yl", list.get(3));
        request.getSession().setAttribute("loginName", zz);
        request.getSession().setAttribute("projectId", projectId);
        return "/youxue/app/index";
    }
}
