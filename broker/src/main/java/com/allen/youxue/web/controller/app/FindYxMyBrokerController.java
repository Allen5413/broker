package com.allen.youxue.web.controller.app;

import com.alibaba.fastjson.JSONObject;
import com.allen.entity.broker.Customer;
import com.allen.entity.brokerproject.BrokerProject;
import com.allen.service.broker.FindBrokerByIdService;
import com.allen.service.broker.FindBrokerByZZService;
import com.allen.service.broker.RecommendBrokerService;
import com.allen.service.brokerproject.FindBrokerAndCustomerNumByProjectIdAndRandomService;
import com.allen.service.brokerproject.FindBrokerProjectByProjectIdService;
import com.allen.service.customer.FindCustomerByZzAndProjectIdHaveBrokerService;
import com.allen.util.UserUtil;
import com.allen.web.controller.BaseController;
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
    @Autowired
    private RecommendBrokerService recommendBrokerService;
    @Autowired
    private FindBrokerByZZService findBrokerByZZService;
    @Autowired
    private FindBrokerProjectByProjectIdService findBrokerProjectByProjectIdService;
    @Autowired
    private FindBrokerAndCustomerNumByProjectIdAndRandomService findBrokerAndCustomerNumByProjectIdAndRandomService;

    /**
     * @return
     */
    @RequestMapping(value = "open")
    public String open(HttpServletRequest request)throws Exception{
        List<Customer> customerList = findCustomerByZzAndProjectIdHaveBrokerService.find(UserUtil.getLoginUserForLoginName(request), 1l);
        if(null != customerList && 0 < customerList.size()){
            Customer customer = customerList.get(0);
            if(null != customer.getBrokerId() && 0 < customer.getBrokerId()) {
                JSONObject broker = findBrokerByIdService.findAttop(customer.getBrokerId());
                request.setAttribute("broker", broker);
            }else{
                //得到当前用户的所在学校code
                JSONObject json = findBrokerByZZService.findAttop(UserUtil.getLoginUserForLoginName(request));
                //推荐经纪人
                List<JSONObject> brokerList = recommendBrokerService.find(json.get("scode").toString(), UserUtil.getLoginUserForProjectId(request));
                request.setAttribute("brokerList", brokerList);
            }
            return "youxue/app/myBroker";
        }else{
            //返回项目经纪人总数，然后随机取10名经纪人供用户选择
            List<BrokerProject> list = findBrokerProjectByProjectIdService.findByState(UserUtil.getLoginUserForProjectId(request), BrokerProject.STATE_PASS);
            request.setAttribute("brokerNum", null == list ? 0 : list.size());
            return "youxue/app/addMyBroker";
        }
    }


    @RequestMapping(value = "findRndomBroker")
    @ResponseBody
    public JSONObject findRndomBroker(HttpServletRequest request, String indexs) throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("list", findBrokerAndCustomerNumByProjectIdAndRandomService.find(UserUtil.getLoginUserForProjectId(request), indexs.split(",")));
        jsonObject.put("state", 0);
        return jsonObject;
    }
}
