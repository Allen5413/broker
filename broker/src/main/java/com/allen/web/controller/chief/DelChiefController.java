package com.allen.web.controller.chief;

import com.alibaba.fastjson.JSONObject;
import com.allen.service.chief.DelChiefService;
import com.allen.util.UserUtil;
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
@RequestMapping(value = "/delChief")
public class DelChiefController extends BaseController {

    @Autowired
    private DelChiefService delChiefService;


    /**
     * @param request
     * @return
     */
    @RequestMapping(value = "del")
    @ResponseBody
    public JSONObject del(HttpServletRequest request, String no) throws Exception {
        JSONObject jsonObject = new JSONObject();
        delChiefService.delBySchoolNo(no, UserUtil.getLoginUserForLoginId(request), UserUtil.getLoginUserForName(request));
        jsonObject.put("state", 0);
        return jsonObject;
    }
}
