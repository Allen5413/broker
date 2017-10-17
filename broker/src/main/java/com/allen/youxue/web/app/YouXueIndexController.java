package com.allen.youxue.web.app;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Allen on 2017/10/17.
 */
@Controller
@RequestMapping(value = "/youxueApp/index")
public class YouXueIndexController {

    @RequestMapping(value = "open")
    public String find(HttpServletRequest request, String zz) throws Exception {
        return "/youxue/app/index";
    }
}
