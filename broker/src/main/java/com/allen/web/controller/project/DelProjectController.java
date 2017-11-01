package com.allen.web.controller.project;

import com.alibaba.fastjson.JSONObject;
import com.allen.service.project.DelProjectService;
import com.allen.web.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Allen on 2017/6/28.
 */
@Controller
@RequestMapping(value = "/delProject")
public class DelProjectController extends BaseController {

    @Autowired
    private DelProjectService delProjectService;

    /**
     * @param request
     * @return
     */
    @RequestMapping(value = "del")
    @ResponseBody
    public JSONObject del(HttpServletRequest request, long id) throws Exception {
        JSONObject jsonObject = new JSONObject();
        delProjectService.del(id);
        jsonObject.put("state", 0);
        return jsonObject;
    }
}
