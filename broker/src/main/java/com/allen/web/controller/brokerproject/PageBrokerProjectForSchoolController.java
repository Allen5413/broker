package com.allen.web.controller.brokerproject;

import com.allen.dao.PageInfo;
import com.allen.service.brokerproject.PageBrokerProjectService;
import com.allen.service.project.FindProjectAllService;
import com.allen.util.StringUtil;
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
@RequestMapping(value = "/pageBrokerProjectForSchool")
public class PageBrokerProjectForSchoolController extends BaseController {

    @Autowired
    private PageBrokerProjectService pageBrokerProjectService;
    @Autowired
    private FindProjectAllService findProjectAllService;

    @RequestMapping(value = "page")
    public String find(HttpServletRequest request, Long projectId,
                       @RequestParam(value = "name", required = false)String name,
                       @RequestParam(value = "method", required = false)String method) throws Exception {
        request.setAttribute("projectList", findProjectAllService.find());
        request.setAttribute("reqParams", super.getParameters(request));
        if("search".equals(method)) {
            PageInfo pageInfo = super.getPageInfo(request);
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("t.pId", projectId);
            params.put("t.name", StringUtil.isEmpty(name) ? null : new Object[]{"like", "%"+name+"%"});
            Map<String, Boolean> sortMap = new HashMap<String, Boolean>();
            sortMap.put("t.name", true);
            pageInfo = pageBrokerProjectService.findForSchool(pageInfo, params, sortMap);
            request.setAttribute("pageInfo", pageInfo);
        }
        return "/brokerproject/pageSchool";
    }
}
