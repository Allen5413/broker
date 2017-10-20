package com.allen.youxue.web.controller.app;

import com.alibaba.fastjson.JSONObject;
import com.allen.dao.PageInfo;
import com.allen.service.broker.FindBrokerByZZService;
import com.allen.util.UserUtil;
import com.allen.web.controller.BaseController;
import com.allen.youxue.entity.product.Product;
import com.allen.youxue.entity.product.ProductDate;
import com.allen.youxue.entity.team.Team;
import com.allen.youxue.service.product.FindYxProductAllService;
import com.allen.youxue.service.product.FindYxProductByIdService;
import com.allen.youxue.service.productdate.FindYxProductDateByIdService;
import com.allen.youxue.service.team.FindYxTeamByIdService;
import com.allen.youxue.service.team.FindYxTeamByZzAndIsHeadService;
import com.allen.youxue.service.team.PageYxTeamService;
import com.allen.youxue.service.teamimg.FindYxTeamImgByZzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Allen on 2017/10/19.
 */
@Controller
@RequestMapping(value = "/youxueApp/findTeam")
public class FindYxTeamController extends BaseController {

    @Autowired
    private FindYxProductAllService findYxProductAllService;
    @Autowired
    private PageYxTeamService pageYxTeamService;
    @Autowired
    private FindYxTeamByIdService findYxTeamByIdService;
    @Autowired
    private FindYxProductDateByIdService findYxProductDateByIdService;
    @Autowired
    private FindYxProductByIdService findYxProductByIdService;
    @Autowired
    private FindBrokerByZZService findBrokerByZZService;
    @Autowired
    private FindYxTeamByZzAndIsHeadService findYxTeamByZzAndIsHeadService;
    @Autowired
    private FindYxTeamImgByZzService findYxTeamImgByZzService;

    @RequestMapping(value = "find")
    public String find(HttpServletRequest request,
                       @RequestParam(value = "productId", required = false)Long productId) throws Exception {
        PageInfo pageInfo = super.getPageInfo(request);
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("c.project_id", 1l);
        params.put("t.is_head", Team.ISHEAD_YES);
        params.put("p.id", productId);
        Map<String, Boolean> sortMap = new HashMap<String, Boolean>();
        sortMap.put("t.id", false);
        pageInfo.setCountOfCurrentPage(999999);
        pageInfo = pageYxTeamService.findPage(pageInfo, params, sortMap, true);
        request.setAttribute("produceList", findYxProductAllService.find());
        request.setAttribute("list", pageInfo.getPageResults());
        return "/youxue/app/listTeam";
    }

    @RequestMapping(value = "findInfo")
    public String findInfo(HttpServletRequest request, long teamId) throws Exception {
        JSONObject team = findYxTeamByIdService.findAttop(teamId);
        ProductDate productDate = findYxProductDateByIdService.find(Long.parseLong(team.get("productDateId").toString()));
        Product product = findYxProductByIdService.find(productDate.getProductId());

        //查询团长风采
        Map<String, List<String>> imgMap = findYxTeamImgByZzService.findImgByZz(team.get("zz").toString());

        //查询团员信息
        PageInfo pageInfo = super.getPageInfo(request);
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("t.parent_id", teamId);
        params.put("t.state", Team.STATE_FEE);
        params.put("c.project_id", 1l);
        Map<String, Boolean> sortMap = new HashMap<String, Boolean>();
        sortMap.put("t.id", false);
        pageInfo.setCountOfCurrentPage(999999);
        pageInfo = pageYxTeamService.findPage(pageInfo, params, sortMap, false);

        request.setAttribute("team", team);
        request.setAttribute("imgMap", imgMap);
        request.setAttribute("productDate", productDate);
        request.setAttribute("product", product);
        request.setAttribute("teamList", pageInfo.getPageResults());
        return "/youxue/app/teamInfo";
    }

    @RequestMapping(value = "user")
    public String find(HttpServletRequest request) throws Exception {
        //查询个人信息
        JSONObject user = findBrokerByZZService.findAttop(UserUtil.getLoginUserForLoginName(request));
        //查询报名产品的信息
        PageInfo pageInfo = super.getPageInfo(request);
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("t.zz", UserUtil.getLoginUserForLoginName(request));
        params.put("c.project_id", 1l);
        Map<String, Boolean> sortMap = new HashMap<String, Boolean>();
        sortMap.put("t.id", false);
        pageInfo = pageYxTeamService.findPage(pageInfo, params, sortMap, false);
        request.setAttribute("user", user);
        request.setAttribute("teamList", pageInfo.getPageResults());
        //查询登录用户是不是校花团长
        List<Team> teamList = findYxTeamByZzAndIsHeadService.find(UserUtil.getLoginUserForLoginName(request), Team.ISHEAD_YES);
        request.setAttribute("isHead", null != teamList && 0 < teamList.size() ? true : false);
        return "/youxue/app/user";
    }
}
