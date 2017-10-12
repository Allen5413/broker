package com.allen.web.controller.brokerproject;

import com.alibaba.fastjson.JSONObject;
import com.allen.dao.PageInfo;
import com.allen.entity.brokerproject.BrokerProject;
import com.allen.service.brokerproject.FindBrokerProjectForSchoolService;
import com.allen.service.brokerproject.PageBrokerProjectService;
import com.allen.service.project.FindProjectAllService;
import com.allen.web.controller.BaseController;
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
@RequestMapping(value = "/findBrokerProjectForSchool")
public class FindBrokerProjectForSchoolController extends BaseController {

    @Autowired
    private FindBrokerProjectForSchoolService findBrokerProjectForSchoolService;
    @Autowired
    private FindProjectAllService findProjectAllService;

    @RequestMapping(value = "find")
    public String find(HttpServletRequest request, Long projectId,
                       @RequestParam(value = "name", required = false)String name,
                       @RequestParam(value = "method", required = false)String method) throws Exception {
        request.setAttribute("projectList", findProjectAllService.find());
        request.setAttribute("reqParams", super.getParameters(request));
        if("search".equals(method)) {
            Map<String, JSONObject> map = findBrokerProjectForSchoolService.find(projectId, name);
            request.setAttribute("map", map);
        }
        return "/brokerproject/listSchool";
    }
}
