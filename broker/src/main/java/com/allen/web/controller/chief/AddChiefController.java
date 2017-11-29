package com.allen.web.controller.chief;

import com.alibaba.fastjson.JSONObject;
import com.allen.dao.PageInfo;
import com.allen.entity.user.User;
import com.allen.service.chief.AddChiefService;
import com.allen.service.customer.AddCustomerService;
import com.allen.util.UserUtil;
import com.allen.web.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Allen on 2017/6/28.
 */
@Controller
@RequestMapping(value = "/addChief")
public class AddChiefController extends BaseController {

    @Autowired
    private AddChiefService addChiefService;

    @RequestMapping(value = "open")
    public String open() throws Exception {
        return "/chief/add";
    }

    /**
     * @param request
     * @return
     */
    @RequestMapping(value = "add")
    @ResponseBody
    public JSONObject add(HttpServletRequest request, String zz, String no) throws Exception {
        JSONObject jsonObject = new JSONObject();
        addChiefService.add(zz, no, UserUtil.getLoginUserForLoginId(request), UserUtil.getLoginUserForName(request));
        jsonObject.put("state", 0);
        return jsonObject;
    }
}
