package com.allen.youxue.web.team;

import com.alibaba.fastjson.JSONObject;
import com.allen.util.StringUtil;
import com.allen.util.UserUtil;
import com.allen.web.controller.BaseController;
import com.allen.youxue.entity.product.Product;
import com.allen.youxue.entity.team.Team;
import com.allen.youxue.service.product.FindYxProductAllService;
import com.allen.youxue.service.product.FindYxProductByIdService;
import com.allen.youxue.service.team.AddYxTeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Allen on 2017/10/17 0017.
 */
@Controller
@RequestMapping(value = "/youxue/addTeam")
public class AddYxTeamController extends BaseController {

    @Autowired
    private AddYxTeamService addYxTeamService;
    @Autowired
    private FindYxProductAllService findYxProductAllService;
    @Autowired
    private FindYxProductByIdService findYxProductByIdService;

    /**
     * @return
     */
    @RequestMapping(value = "open")
    public String open(HttpServletRequest request, @RequestParam(value = "reqParams", required = false)String reqParams)throws Exception{
        request.setAttribute("reqParams", !StringUtil.isEmpty(reqParams) ? new String(reqParams.getBytes("iso-8859-1"), "gbk") : "");
        //查询所有产品
        request.setAttribute("productList", findYxProductAllService.find());
        return "youxue/team/add";
    }

    /**
     * @param request
     * @return
     */
    @RequestMapping(value = "addHead")
    @ResponseBody
    public JSONObject addHead(HttpServletRequest request, Team team, String brokerZz, long productId) throws Exception {
        JSONObject jsonObject = new JSONObject();
        if(null != team) {
            Product product = findYxProductByIdService.find(productId);
            team.setCreator(UserUtil.getLoginUserForName(request));
            team.setOperator(UserUtil.getLoginUserForName(request));
            addYxTeamService.addHead(team, brokerZz, product.getProjectId()
                    , UserUtil.getLoginUserForLoginId(request));
        }
        jsonObject.put("state", 0);
        return jsonObject;
    }
}
