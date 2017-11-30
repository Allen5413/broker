package com.allen.web.controller.chief;

import com.alibaba.fastjson.JSONObject;
import com.allen.entity.basic.School;
import com.allen.entity.chief.Chief;
import com.allen.service.basic.school.FindSchoolByNoService;
import com.allen.service.broker.FindBrokerByZZService;
import com.allen.service.chief.FindChiefByNoService;
import com.allen.util.DateUtil;
import com.allen.util.StringUtil;
import com.allen.web.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;

/**
 * Created by Allen on 2017/6/28.
 */
@Controller
@RequestMapping(value = "/findChief")
public class FindChiefController extends BaseController {

    @Autowired
    private FindSchoolByNoService findSchoolByNoService;
    @Autowired
    private FindBrokerByZZService findBrokerByZZService;
    @Autowired
    private FindChiefByNoService findChiefByNoService;

    @RequestMapping(value = "info")
    public String info(HttpServletRequest request, String no) throws Exception {
        //查询学校信息
        School school = findSchoolByNoService.find(no);
        request.setAttribute("school", school);
        request.setAttribute("activePercent", 0 == school.getActiveNum() ? 0 : new BigDecimal(school.getActiveNum()*100).divide(new BigDecimal(school.getNum()), 2, BigDecimal.ROUND_HALF_UP));
        //查询总监信息
        Chief chief = findChiefByNoService.find(no);
        if(null != chief){
            if(!StringUtil.isEmpty(chief.getZz())) {
                JSONObject json = findBrokerByZZService.findAttop(chief.getZz());
                request.setAttribute("cName", json.get("realname"));
                request.setAttribute("cMobile", json.get("mobile"));
                request.setAttribute("createTime", chief.getCreateTime());
            }
            //查询推荐人信息
            if(!StringUtil.isEmpty(chief.getRecommendZz())) {
                JSONObject json = findBrokerByZZService.findAttop(chief.getRecommendZz());
                request.setAttribute("rName", json.get("realname"));
                request.setAttribute("rMobile", json.get("mobile"));
            }
        }
        //获取当前时间
        request.setAttribute("date", DateUtil.getShortNowTime().toString().substring(0, 10)+" 00:00");
        return "/chief/detail";
    }

}
