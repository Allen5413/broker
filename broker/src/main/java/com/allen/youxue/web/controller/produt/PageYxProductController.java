package com.allen.youxue.web.controller.produt;

import com.allen.dao.PageInfo;
import com.allen.web.controller.BaseController;
import com.allen.youxue.service.product.PageYxProductService;
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
@RequestMapping(value = "/youxue/pageProduct")
public class PageYxProductController extends BaseController {

    @Autowired
    private PageYxProductService pageYxProductService;

    @RequestMapping(value = "page")
    public String find(HttpServletRequest request) throws Exception {
        PageInfo pageInfo = super.getPageInfo(request);
        Map<String, Object> params = new HashMap<String, Object>();
        Map<String, Boolean> sortMap = new HashMap<String, Boolean>();
        sortMap.put("p.id", false);
        pageInfo = pageYxProductService.find(pageInfo, params, sortMap);
        request.setAttribute("pageInfo", pageInfo);
        request.setAttribute("reqParams", super.getParameters(request));
        return "/youxue/product/page";
    }
}
