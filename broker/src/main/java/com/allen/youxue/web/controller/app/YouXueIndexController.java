package com.allen.youxue.web.controller.app;

import com.alibaba.fastjson.JSONObject;
import com.allen.dao.PageInfo;
import com.allen.entity.broker.Customer;
import com.allen.service.broker.FindBrokerByZZService;
import com.allen.service.broker.RecommendBrokerService;
import com.allen.service.customer.FindCustomerByZzAndProjectIdHaveBrokerService;
import com.allen.service.customerdaylogincount.AddCusotmerDayLoginCountService;
import com.allen.service.project.EditProjectVisitCountService;
import com.allen.service.project.FindProjectByIdService;
import com.allen.util.StringUtil;
import com.allen.util.UserUtil;
import com.allen.web.controller.BaseController;
import com.allen.youxue.entity.team.Team;
import com.allen.youxue.service.product.FindYxProductForAppService;
import com.allen.youxue.service.team.FindYxTeamHeadService;
import com.allen.youxue.service.team.PageYxTeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
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
    @Autowired
    private PageYxTeamService pageYxTeamService;

    @RequestMapping(value = "open")
    public String find(HttpServletRequest request, long projectId,
                       @RequestParam(value = "zz", required = false)String zz,
                       @RequestParam(value = "brokerZz", required = false)String brokerZz,
                       @RequestParam(value = "notCount", required = false)String notCount) throws Exception {
        String loginName = UserUtil.getLoginUserForLoginName(request);
        List<Customer> customerList = findCustomerByZzAndProjectIdHaveBrokerService.find(zz, projectId);
        boolean isHaveBroker = null != customerList && 0 < customerList.size() ? true : false;
        if(StringUtil.isEmpty(notCount)) {
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
        if(StringUtil.isEmpty(loginName) || (!loginName.equals(zz) && !StringUtil.isEmpty(zz))) {
            request.getSession().setAttribute("loginName", zz);
        }
        request.getSession().setAttribute("projectId", projectId);
        if(!StringUtil.isEmpty(brokerZz)){
            request.getSession().setAttribute("brokerZz", brokerZz);
        }
        //查询人气校花
        PageInfo pageInfo = super.getPageInfo(request);
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("c.project_id", projectId);
        params.put("t.is_head", Team.ISHEAD_YES);
        Map<String, Boolean> sortMap = new HashMap<String, Boolean>();
        sortMap.put("t.visit_count", false);
        pageInfo.setCountOfCurrentPage(3);
        pageInfo = pageYxTeamService.findPage(pageInfo, params, sortMap, true);
        request.setAttribute("teamList", pageInfo.getPageResults());
        return "/youxue/app/index";
    }
}
