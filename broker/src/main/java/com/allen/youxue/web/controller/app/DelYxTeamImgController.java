package com.allen.youxue.web.controller.app;

import com.alibaba.fastjson.JSONObject;
import com.allen.web.controller.BaseController;
import com.allen.youxue.service.teamimg.DelYxTeamImgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Allen on 2017/11/8.
 */
@Controller
@RequestMapping(value = "/youxueApp/delYxTeamImg")
public class DelYxTeamImgController extends BaseController {

    @Autowired
    private DelYxTeamImgService delYxTeamImgService;

    @RequestMapping(value = "del")
    @ResponseBody
    public JSONObject del(HttpServletRequest request, String path, String smallPath)throws Exception{
        JSONObject jsonObject = new JSONObject();
        delYxTeamImgService.del(request, path, smallPath);
        jsonObject.put("state", 0);
        return jsonObject;
    }
}
