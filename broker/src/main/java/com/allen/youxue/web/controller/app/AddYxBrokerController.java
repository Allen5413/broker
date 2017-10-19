package com.allen.youxue.web.controller.app;

import com.alibaba.fastjson.JSONObject;
import com.allen.base.exception.BusinessException;
import com.allen.entity.broker.Broker;
import com.allen.entity.brokerproject.BrokerProject;
import com.allen.service.broker.AddBrokerService;
import com.allen.service.broker.CheckApplyBrokerIsMaxService;
import com.allen.service.broker.FindBrokerByZZService;
import com.allen.service.brokerproject.FindBrokerProjectByBIdAndPIdService;
import com.allen.util.StringUtil;
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
    @Autowired
    private CheckApplyBrokerIsMaxService checkApplyBrokerIsMaxService;

    /**
     * @return
     */
    @RequestMapping(value = "open")
    public String open(HttpServletRequest request)throws Exception{
        String msg = "";
        //检查经纪人学校是否到达申请上限
        JSONObject broker2 = findBrokerByZZService.findAttop(UserUtil.getLoginUserForLoginName(request));
        String sCode = broker2.get("scode").toString();
        String mobile = broker2.get("mobile").toString();
        if(StringUtil.isEmpty(sCode) || StringUtil.isEmpty(mobile)){
            msg = "请到个人中心完善手机、学校等联系方式的绑定。";
        }else {
            if (checkApplyBrokerIsMaxService.check(1l, broker2.get("scode").toString(), Integer.parseInt(broker2.get("snum").toString()))){
                msg = "很抱歉，每个学校经纪人名额有限，贵校本项目已不再接收新的申请。";
            }
        }
        Broker broker = findBrokerByZZService.find(UserUtil.getLoginUserForLoginName(request));
        if(null != broker) {
            BrokerProject brokerProject = findBrokerProjectByBIdAndPIdService.find(broker.getId(), 1l);
            if(null != brokerProject){
                msg = "您目前已是游学项目的经纪人";
            }
            if(Broker.ISBLACK_YES == broker.getIsBlack()){
                msg = "对不起，您已被取消申请新项目经纪人资格";
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
