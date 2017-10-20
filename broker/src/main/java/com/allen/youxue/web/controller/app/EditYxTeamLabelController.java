package com.allen.youxue.web.controller.app;

import com.alibaba.fastjson.JSONObject;
import com.allen.util.UserUtil;
import com.allen.web.controller.BaseController;
import com.allen.youxue.entity.team.Team;
import com.allen.youxue.service.team.EditYxTeamLabelByZzService;
import com.allen.youxue.service.team.FindYxTeamByZzAndIsHeadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Allen on 2017/10/20.
 */
@Controller
@RequestMapping(value = "/youxueApp/editTeamLabel")
public class EditYxTeamLabelController extends BaseController {

    @Autowired
    private FindYxTeamByZzAndIsHeadService findYxTeamByZzAndIsHeadService;
    @Autowired
    private EditYxTeamLabelByZzService editYxTeamLabelByZzService;

    /**
     * @return
     */
    @RequestMapping(value = "open")
    public String open(HttpServletRequest request)throws Exception{
        List<Team> teamList = findYxTeamByZzAndIsHeadService.find(UserUtil.getLoginUserForLoginName(request), Team.ISHEAD_YES);
        if(null != teamList && 0 < teamList.size()){
            Team team = teamList.get(0);
            request.setAttribute("team", team);
        }
        return "youxue/app/editTeamLabel";
    }

    /**
     * @param request
     * @return
     */
    @RequestMapping(value = "editLabel")
    @ResponseBody
    public JSONObject editLabel(HttpServletRequest request, String label) throws Exception {
        JSONObject jsonObject = new JSONObject();
        editYxTeamLabelByZzService.edit(label, UserUtil.getLoginUserForLoginName(request));
        jsonObject.put("state", 0);
        return jsonObject;
    }
}
