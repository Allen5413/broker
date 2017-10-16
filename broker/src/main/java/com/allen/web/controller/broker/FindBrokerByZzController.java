package com.allen.web.controller.broker;

import com.alibaba.fastjson.JSONObject;
import com.allen.entity.broker.Broker;
import com.allen.service.broker.FindBrokerByZZService;
import com.allen.service.customer.FindCustomerByBrokerIdService;
import com.allen.service.project.FindProjectByBrokerService;
import com.allen.web.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * Created by Allen on 2017/10/16.
 */
@Controller
@RequestMapping(value = "/findBrokerByZz")
public class FindBrokerByZzController extends BaseController {

    @Autowired
    private FindBrokerByZZService findBrokerByZZService;
    @Autowired
    private FindProjectByBrokerService findProjectByBrokerService;
    @Autowired
    private FindCustomerByBrokerIdService findCustomerByBrokerIdService;

    @RequestMapping(value = "find")
    public String find(HttpServletRequest request, String zz) throws Exception {
        JSONObject json = findBrokerByZZService.findAttop(zz);
        Broker broker = findBrokerByZZService.find(zz);
        //查询经纪人关联的项目
        List<Map> projectList = findProjectByBrokerService.find(broker.getId());
        if(null != projectList && 0 < projectList.size()){
            Map map = projectList.get(0);
            //查询客户信息
            List<JSONObject> customerList = findCustomerByBrokerIdService.find(broker.getId(), Long.parseLong(map.get("id").toString()));
            request.setAttribute("customerList", customerList);
        }
        request.setAttribute("broker", json);
        request.setAttribute("projectList", projectList);
        return "/broker/info";
    }
}
