package com.allen.web.controller.brokerproject;

import com.allen.service.basic.school.FindSchoolByNoService;
import com.allen.service.brokerproject.FindBrokerProjectBySchoolCodeService;
import com.allen.web.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Allen on 2017/10/12.
 */
@Controller
@RequestMapping(value = "/findBrokerProjectBySchoolCode")
public class FindBrokerProjectBySchoolCodeController extends BaseController {

    @Autowired
    private FindBrokerProjectBySchoolCodeService findBrokerProjectBySchoolCodeService;
    @Autowired
    private FindSchoolByNoService findSchoolByNoService;

    @RequestMapping(value = "find")
    public String find(HttpServletRequest request, Long projectId, String schoolCode) throws Exception {
        request.setAttribute("reqParams", super.getParameters(request));
        request.setAttribute("list", findBrokerProjectBySchoolCodeService.find(projectId, schoolCode));
        return "/brokerproject/listBrokerBySchoolCode";
    }

    @RequestMapping(value = "findGroupByProject")
    public String findGroupByProject(HttpServletRequest request, String no) throws Exception {
        request.setAttribute("school", findSchoolByNoService.find(no));
        request.setAttribute("list", findBrokerProjectBySchoolCodeService.findGroupByProject(no));
        return "/brokerproject/listGroupByProject";
    }
}
