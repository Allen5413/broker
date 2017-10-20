package com.allen.youxue.web.controller.app;

import com.alibaba.fastjson.JSONObject;
import com.allen.entity.broker.Broker;
import com.allen.entity.broker.Customer;
import com.allen.service.broker.FindBrokerByIdService;
import com.allen.service.customer.FindCustomerByZzAndProjectIdHaveBrokerService;
import com.allen.util.UserUtil;
import com.allen.web.controller.BaseController;
import com.allen.youxue.entity.team.Team;
import com.allen.youxue.service.team.EditYxTeamLabelByZzService;
import com.allen.youxue.service.team.FindYxTeamByZzAndIsHeadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Allen on 2017/10/20.
 */
@Controller
@RequestMapping(value = "/youxueApp/findMyBroker")
public class FindYxMyBrokerController extends BaseController {

    @Autowired
    private FindCustomerByZzAndProjectIdHaveBrokerService findCustomerByZzAndProjectIdHaveBrokerService;
    @Autowired
    private FindBrokerByIdService findBrokerByIdService;

    /**
     * @return
     */
    @RequestMapping(value = "open")
    public String open(HttpServletRequest request)throws Exception{
        List<Customer> customerList = findCustomerByZzAndProjectIdHaveBrokerService.find(UserUtil.getLoginUserForLoginName(request), 1l);
        if(null != customerList && 0 < customerList.size()){
            Customer customer = customerList.get(0);
            JSONObject broker = findBrokerByIdService.findAttop(customer.getBrokerId());
            request.setAttribute("broker", broker);
        }
        return "youxue/app/myBroker";
    }
}
