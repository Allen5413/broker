package com.allen.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Allen on 2015/5/18.
 */
@Controller
public class LogoutController {

    @RequestMapping(value = "logout")
    public void logout(HttpServletRequest request, HttpServletResponse response)throws Exception{
        this.removeSession(request);
        response.sendRedirect("/");
    }

    @RequestMapping(value = "/youxue/logout")
    public void youxueLogout(HttpServletRequest request, HttpServletResponse response)throws Exception{
        this.removeSession(request);
        response.sendRedirect("/youxue/login");
    }

    private void removeSession(HttpServletRequest request){
        request.getSession().removeAttribute("loginName");
        request.getSession().removeAttribute("name");
        request.getSession().removeAttribute("userId");
        request.getSession().removeAttribute("menuMap");
        request.getSession().removeAttribute("type");
    }
}
