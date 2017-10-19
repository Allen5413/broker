package com.allen.youxue.web.controller.app;

import com.alibaba.fastjson.JSONObject;
import com.allen.base.exception.BusinessException;
import com.allen.entity.broker.Broker;
import com.allen.entity.broker.Customer;
import com.allen.service.broker.FindBrokerByIdService;
import com.allen.service.broker.FindBrokerByZZService;
import com.allen.service.customer.FindCustomerByZzAndProjectIdHaveBrokerService;
import com.allen.util.UserUtil;
import com.allen.web.controller.BaseController;
import com.allen.youxue.dao.team.YxTeamDao;
import com.allen.youxue.entity.product.Product;
import com.allen.youxue.service.product.FindYxProductByIdService;
import com.allen.youxue.service.team.FindYxTeamByIdService;
import com.allen.youxue.service.team.SignUpYxTeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Allen on 2017/10/19 0019.
 */
@Controller
@RequestMapping(value = "/youxueApp/signUpTeam")
public class SignUpYxTeamController extends BaseController {

    @Autowired
    private FindYxTeamByIdService findYxTeamByIdService;
    @Autowired
    private FindBrokerByZZService findBrokerByZZService;
    @Autowired
    private FindBrokerByIdService findBrokerByIdService;
    @Autowired
    private FindYxProductByIdService findYxProductByIdService;
    @Autowired
    private YxTeamDao yxTeamDao;
    @Autowired
    private FindCustomerByZzAndProjectIdHaveBrokerService findCustomerByZzAndProjectIdHaveBrokerService;
    @Autowired
    private SignUpYxTeamService signUpYxTeamService;

    @RequestMapping(value = "open")
    public String open(HttpServletRequest request, long productId) throws Exception {
        request.setAttribute("product", findYxProductByIdService.find(productId));
        return "/youxue/app/addTeamShuoMing";
    }

    @RequestMapping(value = "openSignUp")
    public String openSignUp(HttpServletRequest request, long productId, long teamHeadId) throws Exception {
        JSONObject teamHead = findYxTeamByIdService.findAttop(teamHeadId);
        JSONObject team = findBrokerByZZService.findAttop(UserUtil.getLoginUserForLoginName(request));
        Product product = findYxProductByIdService.find(productId);
        List<Customer> customerList = findCustomerByZzAndProjectIdHaveBrokerService.find(UserUtil.getLoginUserForLoginName(request), 1l);
        if(null != customerList && 0 < customerList.size()){
            JSONObject broker = findBrokerByIdService.findAttop(customerList.get(0).getBrokerId());
            request.setAttribute("broker", broker);
        }
        request.setAttribute("teamHead", teamHead);
        request.setAttribute("team", team);
        request.setAttribute("product", product);
        request.setAttribute("teamNum", yxTeamDao.countTeamNum(teamHeadId));
        return "/youxue/app/signUpTeam";
    }

    /**
     * @param request
     * @return
     */
    @RequestMapping(value = "signUp")
    @ResponseBody
    public JSONObject signUp(HttpServletRequest request, String yyDate, String brokerZz, long teamHeadId) throws Exception {
        JSONObject jsonObject = new JSONObject();
        Broker broker = findBrokerByZZService.find(brokerZz);
        if(broker == null){
            throw new BusinessException("您的经纪人不存在");
        }
        signUpYxTeamService.signUp(1l, UserUtil.getLoginUserForLoginName(request), yyDate, broker.getId(), teamHeadId);
        jsonObject.put("state", 0);
        return jsonObject;
    }
}
