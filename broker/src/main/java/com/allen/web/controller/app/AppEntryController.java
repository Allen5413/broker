package com.allen.web.controller.app;

import com.alibaba.fastjson.JSONObject;
import com.allen.base.config.ConfigProp;
import com.allen.base.exception.BusinessException;
import com.allen.service.app.broker.AddBrokerForAppService;
import com.allen.service.app.broker.FindBrokerBySchoolNoForAppService;
import com.allen.service.app.broker.FindBrokerByZzForAppService;
import com.allen.service.app.broker.FindBrokerNumBySchoolCodeForAppService;
import com.allen.service.app.chief.FindChiefBySchoolNoForAppService;
import com.allen.service.app.customer.EditCustomerIsStarForAppService;
import com.allen.service.app.customer.EditCustomerRemarkForAppService;
import com.allen.service.app.customer.FindCustomerByBrokerIdForAppService;
import com.allen.service.app.customer.FindCustomerByIdForAppService;
import com.allen.service.app.project.FindProjectByIdForAppService;
import com.allen.util.MD5Util;
import com.allen.util.StringUtil;
import com.allen.web.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Allen on 2017/8/7.
 */
@RestController
public class AppEntryController extends BaseController {

    @Autowired
    private FindBrokerByZzForAppService findBrokerByZzForAppService;
    @Autowired
    private FindCustomerByBrokerIdForAppService findCustomerByBrokerIdForAppService;
    @Autowired
    private EditCustomerRemarkForAppService editCustomerRemarkForAppService;
    @Autowired
    private FindProjectByIdForAppService findProjectByIdForAppService;
    @Autowired
    private AddBrokerForAppService addBrokerForAppService;
    @Autowired
    private FindBrokerNumBySchoolCodeForAppService findBrokerNumBySchoolCodeForAppService;
    @Autowired
    private EditCustomerIsStarForAppService editCustomerIsStarForAppService;
    @Autowired
    private FindCustomerByIdForAppService findCustomerByIdForAppService;
    @Autowired
    private ConfigProp configProp;
    @Autowired
    private FindBrokerBySchoolNoForAppService findBrokerBySchoolNoForAppService;
    @Autowired
    private FindChiefBySchoolNoForAppService findChiefBySchoolNoForAppService;

    @RequestMapping(value = "/appEntry")
    public JSONObject entry(HttpServletRequest request) throws Exception {
        String zz = request.getParameter("zz");
        String mac = request.getParameter("mac");
        String notMac = request.getParameter("notMac");
        if(StringUtil.isEmpty(request.getParameter("methodId"))){
            throw new BusinessException("协议号为空");
        }
        Integer methodId = Integer.parseInt(request.getParameter("methodId"));
        String query = request.getQueryString();
        String key = configProp.getAttop().get("key");
        query = query.substring(0, query.indexOf("&mac="));
        if(!mac.equals(MD5Util.getAttopMd5(query + key)) && StringUtil.isEmpty(notMac)){
            throw new BusinessException("mac校验失败");
        }
        JSONObject jsonObject = new JSONObject();
        if(1 == methodId){
            //经纪人页面获取数据
            jsonObject = findBrokerByZzForAppService.find(zz);
        }
        if(2 == methodId){
            //获取经纪人的一个项目下的成员集合
            jsonObject = findCustomerByBrokerIdForAppService.find(request);
        }
        if(3 == methodId){
            //获取经纪人的一个项目下的成员详情
            jsonObject = findCustomerByIdForAppService.find(request);
        }
        if(4 == methodId){
            //编辑一个成员的备注
            jsonObject = editCustomerRemarkForAppService.edit(request);
        }
        if(5 == methodId){
            //点亮一个成员的星星
            jsonObject = editCustomerIsStarForAppService.edit(request);
        }
        if(6 == methodId){
            //获取一所学校最大申请人数和已经申请人数
            jsonObject = findBrokerNumBySchoolCodeForAppService.find(request);
        }
        if(7 == methodId){
            //申请当经纪人
            jsonObject = addBrokerForAppService.add(request);
        }
        if(8 == methodId){
            //获取项目详情
            jsonObject = findProjectByIdForAppService.find(request);
        }
        if(9 == methodId){
            //获取经济人列表接口：（根据学校NO来获取经济人（每次访问随机1批））
            //朱总培训项目用
            jsonObject = findBrokerBySchoolNoForAppService.find(request);
        }
        if(10 == methodId){
            //获取某个经济人接口：（根据ZZ获取）；返回：0不是经济人，1是经济人
            //朱总培训项目用
            jsonObject = findBrokerByZzForAppService.isBroker(request);
        }
        if(11 == methodId){
            //获取一个高校的总监zz
            //朱总培训项目用
            jsonObject = findChiefBySchoolNoForAppService.find(request);
        }
        return jsonObject;
    }
}