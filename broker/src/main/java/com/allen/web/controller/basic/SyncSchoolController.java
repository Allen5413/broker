package com.allen.web.controller.basic;

import com.alibaba.fastjson.JSONObject;
import com.allen.service.basic.school.SyncSchoolService;
import com.allen.util.UserUtil;
import com.allen.web.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Allen on 2017/11/28.
 */
@Controller
@RequestMapping(value = "/syncSchool")
public class SyncSchoolController extends BaseController {

    @Autowired
    private SyncSchoolService syncSchoolService;

    @RequestMapping(value = "open")
    public String find() throws Exception {
        return "/basic/school/sync";
    }

    @RequestMapping(value = "sync")
    @ResponseBody
    public JSONObject findForJSON(HttpServletRequest request) throws Exception {
        JSONObject jsonObject = new JSONObject();
        syncSchoolService.sync(UserUtil.getLoginUserForLoginId(request), UserUtil.getLoginUserForName(request));
        jsonObject.put("state", 0);
        return jsonObject;
    }
}
