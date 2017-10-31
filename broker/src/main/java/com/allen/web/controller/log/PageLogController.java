package com.allen.web.controller.log;

import com.allen.dao.PageInfo;
import com.allen.service.log.PageLogService;
import com.allen.service.project.PageProjectService;
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
@RequestMapping(value = "/pageLog")
public class PageLogController extends BaseController {

    @Autowired
    private PageLogService pageLogService;

    @RequestMapping(value = "page")
    public String find(HttpServletRequest request) throws Exception {
        PageInfo pageInfo = super.getPageInfo(request);
        Map<String, Object> params = new HashMap<String, Object>();
        Map<String, Boolean> sortMap = new HashMap<String, Boolean>();
        sortMap.put("l.id", false);
        pageInfo = pageLogService.find(pageInfo, params, sortMap);
        request.setAttribute("pageInfo", pageInfo);
        request.setAttribute("reqParams", super.getParameters(request));
        return "/log/page";
    }
}
