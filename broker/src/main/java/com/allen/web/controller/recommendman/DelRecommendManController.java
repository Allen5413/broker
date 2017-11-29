package com.allen.web.controller.recommendman;

import com.alibaba.fastjson.JSONObject;
import com.allen.service.recommendman.DelRecommendManService;
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
@RequestMapping(value = "/delRecommendMan")
public class DelRecommendManController extends BaseController {

    @Autowired
    private DelRecommendManService delRecommendManService;

    /**
     * @param request
     * @return
     */
    @RequestMapping(value = "del")
    @ResponseBody
    public JSONObject del(HttpServletRequest request, String no) throws Exception {
        JSONObject jsonObject = new JSONObject();
        delRecommendManService.delBySchoolNo(no, UserUtil.getLoginUserForLoginId(request), UserUtil.getLoginUserForName(request));
        jsonObject.put("state", 0);
        return jsonObject;
    }
}
