package com.allen.youxue.web.controller.app;

import com.allen.web.controller.BaseController;
import com.allen.youxue.service.product.FindYxProductForAppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * Created by Allen on 2017/10/19.
 */
@Controller
@RequestMapping(value = "/youxueApp/findProduct")
public class FindYxProductController extends BaseController {

    @Autowired
    private FindYxProductForAppService findYxProductForAppService;

    @RequestMapping(value = "open")
    public String open(HttpServletRequest request) throws Exception {
        List<Map> list = findYxProductForAppService.find(1l);
        request.setAttribute("bj", list.get(0));
        request.setAttribute("xg", list.get(1));
        request.setAttribute("jq", list.get(2));
        request.setAttribute("yl", list.get(3));
        return "/youxue/app/listProduct";
    }
}
