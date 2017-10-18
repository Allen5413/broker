package com.allen.youxue.web.team;

import com.allen.dao.PageInfo;
import com.allen.service.broker.FindBrokerAllService;
import com.allen.web.controller.BaseController;
import com.allen.youxue.service.product.FindYxProductAllService;
import com.allen.youxue.service.team.FindYxTeamHeadService;
import com.allen.youxue.service.team.PageYxTeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Allen on 2017/6/27.
 */
@Controller
@RequestMapping(value = "/youxue/pageTeam")
public class PageYxTeamController extends BaseController {

    @Autowired
    private PageYxTeamService pageYxTeamService;
    @Autowired
    private FindYxTeamHeadService findYxTeamHeadService;
    @Autowired
    private FindYxProductAllService findYxProductAllService;
    @Autowired
    private FindBrokerAllService findBrokerAllService;

    @RequestMapping(value = "page")
    public String find(HttpServletRequest request,
                       @RequestParam(value = "teamId", required = false)Long teamId,
                       @RequestParam(value = "brokerId", required = false)Long brokerId,
                       @RequestParam(value = "productId", required = false)Long productId,
                       @RequestParam(value = "isHead", required = false)Integer isHead) throws Exception {
        PageInfo pageInfo = super.getPageInfo(request);
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("t.parent_id", teamId);
        params.put("t.is_head", isHead);
        params.put("p.id", productId);
        params.put("b.id", brokerId);
        Map<String, Boolean> sortMap = new HashMap<String, Boolean>();
        sortMap.put("t.id", false);
        pageInfo = pageYxTeamService.findPage(pageInfo, params, sortMap);
        request.setAttribute("pageInfo", pageInfo);
        request.setAttribute("teamHeadList", findYxTeamHeadService.find());
        request.setAttribute("produceList", findYxProductAllService.find());
        request.setAttribute("brokerList", findBrokerAllService.find());
        request.setAttribute("reqParams", super.getParameters(request));
        return "/youxue/team/page";
    }
}
