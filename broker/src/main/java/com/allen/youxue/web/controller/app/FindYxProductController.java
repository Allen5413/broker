package com.allen.youxue.web.controller.app;

import com.allen.dao.PageInfo;
import com.allen.web.controller.BaseController;
import com.allen.youxue.entity.product.Product;
import com.allen.youxue.entity.team.Team;
import com.allen.youxue.service.product.FindYxProductByIdService;
import com.allen.youxue.service.product.FindYxProductForAppService;
import com.allen.youxue.service.productdate.ListYxProductDateByProductIdService;
import com.allen.youxue.service.team.PageYxTeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Allen on 2017/10/19.
 */
@Controller
@RequestMapping(value = "/youxueApp/findProduct")
public class FindYxProductController extends BaseController {

    @Autowired
    private FindYxProductForAppService findYxProductForAppService;
    @Autowired
    private FindYxProductByIdService findYxProductByIdService;
    @Autowired
    private ListYxProductDateByProductIdService listYxProductDateByProductIdService;
    @Autowired
    private PageYxTeamService pageYxTeamService;

    @RequestMapping(value = "open")
    public String open(HttpServletRequest request) throws Exception {
        List<Map> list = findYxProductForAppService.find(1l);
        request.setAttribute("bj", list.get(0));
        request.setAttribute("xg", list.get(1));
        request.setAttribute("jq", list.get(2));
        request.setAttribute("yl", list.get(3));
        return "/youxue/app/listProduct";
    }

    @RequestMapping(value = "findInfo")
    public String findInfo(HttpServletRequest request, long productId, int teamNum) throws Exception {
        Product product = findYxProductByIdService.find(productId);
        PageInfo pageInfo = super.getPageInfo(request);
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("c.project_id", 1l);
        params.put("t.is_head", Team.ISHEAD_YES);
        params.put("p.id", productId);
        Map<String, Boolean> sortMap = new HashMap<String, Boolean>();
        sortMap.put("t.id", false);
        pageInfo.setCountOfCurrentPage(999999);
        pageInfo = pageYxTeamService.findPageForAttop(pageInfo, params, sortMap, false);
        request.setAttribute("product", product);
        request.setAttribute("teamNum", teamNum);
        request.setAttribute("pageInfo", pageInfo);
        request.setAttribute("teamHeadNum", null == pageInfo.getPageResults() ? 0 : pageInfo.getPageResults().size());
        request.setAttribute("productDateList", listYxProductDateByProductIdService.find(productId));
        return "/youxue/app/productInfo";
    }
}
