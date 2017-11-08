package com.allen.youxue.web.controller.team;

import com.alibaba.fastjson.JSONObject;
import com.allen.web.controller.BaseController;
import com.allen.youxue.service.team.DelYxTeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Allen on 2017/11/8.
 */
@Controller
@RequestMapping(value = "/youxue/delYxTeam")
public class DelYxTeamController extends BaseController {

    @Autowired
    private DelYxTeamService delYxTeamService;

    @RequestMapping(value = "del")
    @ResponseBody
    public JSONObject del(HttpServletRequest request, long id)throws Exception{
        JSONObject jsonObject = new JSONObject();
        delYxTeamService.del(request, id);
        jsonObject.put("state", 0);
        return jsonObject;
    }
}
