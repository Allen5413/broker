package com.allen.web.controller;

import com.alibaba.fastjson.JSONObject;
import com.allen.base.config.ConfigProp;
import com.allen.base.exception.BusinessException;
import com.allen.entity.basic.Menu;
import com.allen.entity.basic.Resource;
import com.allen.entity.user.User;
import com.allen.service.basic.menu.FindMenuByIdService;
import com.allen.service.basic.resource.FindResourceByUserIdService;
import com.allen.service.user.user.LoginService;
import com.allen.util.HttpRequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * 系统登录
 * Created by Allen on 2016/12/12.
 */
@Controller
public class LoginController {

    @Autowired
    private LoginService loginService;
    @Autowired
    private FindResourceByUserIdService findResourceByUserIdService;
    @Autowired
    private FindMenuByIdService findMenuByIdService;
    @Autowired
    private ConfigProp configProp;

    @RequestMapping("/")
    public String login(){
        return "/login";
    }

    @RequestMapping("/youxue")
    public String youxueLogin(){
        return "/youxue/login";
    }

    @RequestMapping("/login")
    @ResponseBody
    public JSONObject userLogin(@RequestParam("loginName")String loginName,
                            @RequestParam("pwd")String pwd,
                            HttpServletRequest request)throws Exception{
        JSONObject jsonObject = new JSONObject();
        User user = loginService.login(loginName);
        if(null != user){
            if(!HttpRequestUtil.vaildLogin(loginName, pwd, configProp.getAttop().get("loginUrl"))){
                throw new BusinessException("用户名密码错误！");
            }
            this.setSession(request, user);
            jsonObject.put("state", 0);
        }else{
            throw new BusinessException("zz号不存在");
        }
        return jsonObject;
    }

    @RequestMapping("/openIndex")
    public String index()throws Exception{
        return "/index";
    }

    protected String setSession(HttpServletRequest request, User user)throws Exception{
        request.getSession().setAttribute("userId", user.getId());
        request.getSession().setAttribute("loginName", user.getLoginName());
        request.getSession().setAttribute("name", user.getName());
        request.getSession().setAttribute("phone", user.getPhone());
        request.getSession().setAttribute("state", user.getState());
        request.getSession().setAttribute("type", user.getType());
        //得到用户拥有的菜单资源权限
        Map<String, List<Resource>> menuMap = this.getUserMenu(user.getId());
        request.getSession().setAttribute("menuMap", menuMap);
        return "success";
    }

    protected Map<String, List<Resource>> getUserMenu(long userId)throws Exception{
        Map<String, List<Resource>> menuResourceMap = new LinkedHashMap<String, List<Resource>>();
        //获取用户所关联的资源
        List<Resource> resourceList = findResourceByUserIdService.find(userId);
        if(null != resourceList && 0 < resourceList.size()) {
            for(Resource resource : resourceList) {
                //得到菜单
                Menu menu = findMenuByIdService.find(resource.getMenuId());
                List<Resource> resourceList2 = menuResourceMap.get(menu.getName()+"_"+menu.getIcon());
                if (null == resourceList2) {
                    resourceList2 = new ArrayList<Resource>();
                }
                boolean isExists = false;
                for(Resource resource2 : resourceList2){
                    if(resource2.getId() == resource.getId()){
                        isExists = true;
                        break;
                    }
                }
                if(!isExists){
                    resourceList2.add(resource);
                    menuResourceMap.put(menu.getName()+"_"+menu.getIcon(), resourceList2);
                }
            }
        }
        //排序
        Set<String> keys = menuResourceMap.keySet();
        for (String key:keys){
            List<Resource> resources = menuResourceMap.get(key);
            Collections.sort(resources,new Comparator<Resource>(){
                public int compare(Resource arg0, Resource arg1) {
                    return arg0.getSno()>=arg1.getSno()?1:-1;
                }
            });
        }
        return menuResourceMap;
    }
}














