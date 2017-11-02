package com.allen.web.controller.brokerproject;

import com.alibaba.fastjson.JSONObject;
import com.allen.service.brokerproject.DelBrokerProjectService;
import com.allen.web.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Allen on 2017/11/2.
 */
@Controller
@RequestMapping(value = "/delBrokerProject")
public class DelBrokerProjectController extends BaseController {

    @Autowired
    private DelBrokerProjectService delBrokerProjectService;

    /**
     * @return
     */
    @RequestMapping(value = "del")
    @ResponseBody
    public JSONObject del(Long projectId, String zz) throws Exception {
        JSONObject jsonObject = new JSONObject();
        delBrokerProjectService.delByProjectIdAndZz(projectId, zz);
        jsonObject.put("state", 0);
        return jsonObject;
    }
}
