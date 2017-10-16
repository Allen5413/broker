package com.allen.web.controller.broker;

import com.alibaba.fastjson.JSONObject;
import com.allen.service.broker.RemoveBlackBrokerService;
import com.allen.util.UserUtil;
import com.allen.web.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Allen on 2017/10/16 0016.
 */
@Controller
@RequestMapping(value = "/removeBlackBroker")
public class RemoveBlackBrokerController extends BaseController {

    @Autowired
    private RemoveBlackBrokerService removeBlackBrokerService;

    /**
     * @param request
     * @return
     */
    @RequestMapping(value = "removeBlack")
    @ResponseBody
    public JSONObject removeBlack(HttpServletRequest request, long id) throws Exception {
        JSONObject jsonObject = new JSONObject();
        removeBlackBrokerService.removeBlack(id, UserUtil.getLoginUserForLoginId(request), UserUtil.getLoginUserForName(request));
        jsonObject.put("state", 0);
        return jsonObject;
    }
}
