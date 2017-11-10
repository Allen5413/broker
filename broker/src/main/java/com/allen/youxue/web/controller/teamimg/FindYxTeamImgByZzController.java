package com.allen.youxue.web.controller.teamimg;

import com.allen.service.broker.FindBrokerByZZService;
import com.allen.web.controller.BaseController;
import com.allen.youxue.service.teamimg.FindYxTeamImgByZzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * Created by Allen on 2017/10/17 0017.
 */
@Controller
@RequestMapping(value = "/youxue/findYxTeamImgByZz")
public class FindYxTeamImgByZzController extends BaseController {

    @Autowired
    private FindYxTeamImgByZzService findYxTeamImgByZzService;
    @Autowired
    private FindBrokerByZZService findBrokerByZZService;

    /**
     * @return
     */
    @RequestMapping(value = "open")
    public String open(HttpServletRequest request, String zz)throws Exception{
        //查询团长风采
        Map<String, List<String[]>> imgMap = findYxTeamImgByZzService.findImgByZz(zz);
        request.setAttribute("imgMap", imgMap);
        request.setAttribute("team", findBrokerByZZService.findAttop(zz));
        return "youxue/teamimg/listImg";
    }
}
