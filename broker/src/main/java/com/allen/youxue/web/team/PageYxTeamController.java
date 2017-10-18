package com.allen.youxue.web.team;

import com.allen.dao.PageInfo;
import com.allen.web.controller.BaseController;
import com.allen.youxue.service.team.PageYxTeamService;
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
@RequestMapping(value = "/youxue/pageTeam")
public class PageYxTeamController extends BaseController {

    @Autowired
    private PageYxTeamService pageYxTeamService;

    @RequestMapping(value = "page")
    public String find(HttpServletRequest request) throws Exception {
        PageInfo pageInfo = super.getPageInfo(request);
        Map<String, Object> params = new HashMap<String, Object>();
        Map<String, Boolean> sortMap = new HashMap<String, Boolean>();
        sortMap.put("t.id", false);
        pageInfo = pageYxTeamService.findPage(pageInfo, params, sortMap);
        request.setAttribute("pageInfo", pageInfo);
        request.setAttribute("reqParams", super.getParameters(request));
        return "/youxue/team/page";
    }
}
