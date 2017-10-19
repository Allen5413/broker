package com.allen.youxue.web.controller.app;

import com.allen.dao.PageInfo;
import com.allen.web.controller.BaseController;
import com.allen.youxue.entity.team.Team;
import com.allen.youxue.service.product.FindYxProductAllService;
import com.allen.youxue.service.team.PageYxTeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
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
}
