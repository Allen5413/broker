package com.allen.web.controller.broker;

import com.alibaba.fastjson.JSONObject;
import com.allen.service.broker.RecommendBrokerService;
import com.allen.util.UserUtil;
import com.allen.web.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Allen on 2017/10/19.
 */
@Controller
@RequestMapping(value = "/recommendBroker")
public class RecommendBrokerController extends BaseController {

    @Autowired
    private RecommendBrokerService recommendBrokerService;

    /**
     * @return
     */
    @RequestMapping(value = "randomBroker")
    @ResponseBody
    public JSONObject randomBroker(long projectId) throws Exception {
        JSONObject jsonObject = new JSONObject();
        List<JSONObject> list = recommendBrokerService.randomFind(projectId, 4);
        jsonObject.put("state", 0);
        jsonObject.put("list", list);
        return jsonObject;
    }
}
