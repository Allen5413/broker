package com.allen.web.controller.app;

import com.alibaba.fastjson.JSONObject;
import com.allen.base.exception.BusinessException;
import com.allen.service.app.broker.FindBrokerByZzForAppService;
import com.allen.service.app.customer.EditCustomerRemarkForAppService;
import com.allen.service.app.customer.FindCustomerByBrokerIdForAppService;
import com.allen.service.app.project.FindProjectByIdForAppService;
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

    @RequestMapping(value = "/appEntry")
    public JSONObject entry(HttpServletRequest request) throws Exception {
        String zz = request.getParameter("zz");
        String mac = request.getParameter("mac");
        if(StringUtil.isEmpty(request.getParameter("methodId"))){
            throw new BusinessException("协议号为空");
        }
        Integer methodId = Integer.parseInt(request.getParameter("methodId"));
        String query = request.getQueryString();
//        query = query.substring(0, query.indexOf("&mac="));
//        if(!mac.equals(MD5Util.getAttopMd5(query + key))){
//            //throw new BusinessException("mac校验失败");
//        }
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

        }
        if(4 == methodId){
            //编辑一个成员的备注
            jsonObject = editCustomerRemarkForAppService.edit(request);
        }
        if(5 == methodId){
            //点亮一个成员的星星

        }
        if(6 == methodId){
            //获取一所学校最大申请人数和已经申请人数

        }
        if(7 == methodId){
            //申请当经纪人

        }
        if(8 == methodId){
            //获取项目详情
            jsonObject = findProjectByIdForAppService.find(request);
        }
        return jsonObject;
    }
}