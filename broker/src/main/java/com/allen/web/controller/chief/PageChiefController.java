package com.allen.web.controller.chief;

import com.allen.dao.PageInfo;
import com.allen.service.chief.PageChiefService;
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
@RequestMapping(value = "/pageChief")
public class PageChiefController extends BaseController {

    @Autowired
    private PageChiefService pageChiefService;

    @RequestMapping(value = "page")
    public String find(HttpServletRequest request,
                       @RequestParam(value = "name", required = false)String name,
                       @RequestParam(value = "type", required = false)String type,
                       @RequestParam(value = "provCode", required = false)String provCode,
                       @RequestParam(value = "cityCode", required = false)String cityCode,
                       @RequestParam(value = "areaCode", required = false)String areaCode) throws Exception {
        PageInfo pageInfo = super.getPageInfo(request);
        Map<String, String> paramsMap = new HashMap<String, String>();
        paramsMap.put("name", name);
        paramsMap.put("type", type);
        paramsMap.put("provCode", provCode);
        paramsMap.put("cityCode", cityCode);
        paramsMap.put("areaCode", areaCode);
        pageInfo = pageChiefService.find(pageInfo, paramsMap);
        request.setAttribute("pageInfo", pageInfo);
        request.setAttribute("reqParams", super.getParameters(request));
        return "/chief/pageChief";
    }
}
