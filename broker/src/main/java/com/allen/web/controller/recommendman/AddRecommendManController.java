package com.allen.web.controller.recommendman;

import com.alibaba.fastjson.JSONObject;
import com.allen.service.recommendman.AddRecommendManService;
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
@RequestMapping(value = "/addRecommendMan")
public class AddRecommendManController extends BaseController {

    @Autowired
    private AddRecommendManService addRecommendManService;

    @RequestMapping(value = "open")
    public String open() throws Exception {
        return "/recommendman/add";
    }

    /**
     * @param request
     * @return
     */
    @RequestMapping(value = "add")
    @ResponseBody
    public JSONObject add(HttpServletRequest request, String zz, String no) throws Exception {
        JSONObject jsonObject = new JSONObject();
        addRecommendManService.add(zz, no, UserUtil.getLoginUserForLoginId(request), UserUtil.getLoginUserForName(request));
        jsonObject.put("state", 0);
        return jsonObject;
    }
}
