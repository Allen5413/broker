package com.allen.youxue.web.app;

import com.alibaba.fastjson.JSONObject;
import com.allen.service.project.EditProjectVisitCountService;
import com.allen.service.project.FindProjectByIdService;
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
public class YouXueIndexController {

    @Autowired
    private FindProjectByIdService findProjectByIdService;
    @Autowired
    private EditProjectVisitCountService editProjectVisitCountService;
    @Autowired
    private FindYxTeamHeadService findYxTeamHeadService;

    @RequestMapping(value = "open")
    public String find(HttpServletRequest request, String zz) throws Exception {
        editProjectVisitCountService.edit(1l, 1);
        List<JSONObject> teamHeadList = findYxTeamHeadService.find();
        request.setAttribute("teamHeadCount", null == teamHeadList ? 0 : teamHeadList.size());
        request.setAttribute("visitCount", findProjectByIdService.find(1l).getVisitCount());
        request.getSession().setAttribute("zz", zz);
        return "/youxue/app/index";
    }
}
