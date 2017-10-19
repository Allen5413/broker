package com.allen.youxue.web.controller.app;

import com.alibaba.fastjson.JSONObject;
import com.allen.base.exception.BusinessException;
import com.allen.entity.broker.Broker;
import com.allen.entity.brokerproject.BrokerProject;
import com.allen.service.broker.AddBrokerService;
import com.allen.service.broker.FindBrokerByZZService;
import com.allen.service.brokerproject.FindBrokerProjectByBIdAndPIdService;
import com.allen.util.UserUtil;
import com.allen.web.controller.BaseController;
import com.allen.youxue.entity.team.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Allen on 2017/10/17 0017.
 */
@Controller
@RequestMapping(value = "/youxueApp/addBroker")
public class AddYxBrokerController extends BaseController {

    @Autowired
    private FindBrokerByZZService findBrokerByZZService;
    @Autowired
    private FindBrokerProjectByBIdAndPIdService findBrokerProjectByBIdAndPIdService;
    @Autowired
    private AddBrokerService addBrokerService;

    /**
     * @return
     */
    @RequestMapping(value = "open")
    public String open(HttpServletRequest request)throws Exception{
        String msg = "";
        Broker broker = findBrokerByZZService.find(UserUtil.getLoginUserForLoginName(request));
        if(null != broker) {
            BrokerProject brokerProject = findBrokerProjectByBIdAndPIdService.find(broker.getId(), 1l);
            if(null != brokerProject){
                msg = "你目前已是游学项目的经纪人，不用再申请";
            }
            if(Broker.ISBLACK_YES == broker.getIsBlack()){
                msg = "你已经被管理员拉黑，目前不能再申请项目经纪人";
            }
        }
        request.setAttribute("msg", msg);
        return "youxue/app/openBroker";
    }

    /**
     * @return
     */
    @RequestMapping(value = "openApply")
    public String openApply(HttpServletRequest request)throws Exception{
        JSONObject json = findBrokerByZZService.findAttop(UserUtil.getLoginUserForLoginName(request));
        request.setAttribute("broker", json);
        return "youxue/app/openBrokerApply";
    }

    /**
     * @param request
     * @return
     */
    @RequestMapping(value = "apply")
    @ResponseBody
    public JSONObject apply(HttpServletRequest request) throws Exception {
        JSONObject jsonObject = new JSONObject();
        addBrokerService.add(UserUtil.getLoginUserForLoginName(request), 1l);
        jsonObject.put("state", 0);
        return jsonObject;
    }
}
