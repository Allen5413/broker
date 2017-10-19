package com.allen.youxue.web.controller.productdate;

import com.alibaba.fastjson.JSONObject;
import com.allen.web.controller.BaseController;
import com.allen.youxue.service.productdate.ListYxProductDateByProductIdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Allen on 2017/7/10.
 */
@RestController
public class FindYxProductDateByProductIdController extends BaseController {

    @Autowired
    private ListYxProductDateByProductIdService listYxProductDateByProductIdService;

    @RequestMapping(value = "/youxue/findProductDateByProductId")
    public JSONObject find(long productId) throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("state", 0);
        jsonObject.put("productDateList", listYxProductDateByProductIdService.find(productId));
        return jsonObject;
    }
}
