package com.allen.youxue.web.controller.team;

import com.alibaba.fastjson.JSONObject;
import com.allen.util.StringUtil;
import com.allen.util.UserUtil;
import com.allen.web.controller.BaseController;
import com.allen.youxue.service.team.EditYxTeamService;
import com.allen.youxue.service.team.FindYxTeamByIdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Allen on 2017/10/18.
 */
@Controller
@RequestMapping(value = "/youxue/editTeam")
public class EditYxTeamController extends BaseController {

    @Autowired
    private EditYxTeamService editYxTeamService;
    @Autowired
    private FindYxTeamByIdService findYxTeamByIdService;

    /**
     * @return
     */
    @RequestMapping(value = "open")
    public String open(HttpServletRequest request, long id,
                       @RequestParam(value = "reqParams", required = false)String reqParams)throws Exception{
        request.setAttribute("reqParams", !StringUtil.isEmpty(reqParams) ? new String(reqParams.getBytes("iso-8859-1"), "gbk") : "");
        request.setAttribute("team", findYxTeamByIdService.find(id));
        return "youxue/team/editState";
    }

    /**
     * @param request
     * @return
     */
    @RequestMapping(value = "editState")
    @ResponseBody
    public JSONObject editState(HttpServletRequest request, long id, int state, String remark) throws Exception {
        JSONObject jsonObject = new JSONObject();
        editYxTeamService.editState(id, state, remark, UserUtil.getLoginUserForLoginId(request), UserUtil.getLoginUserForName(request));
        jsonObject.put("state", 0);
        return jsonObject;
    }

    /**
     * @return
     */
    @RequestMapping(value = "openEditQq")
    public String openEditQq(HttpServletRequest request, long id,
                       @RequestParam(value = "reqParams", required = false)String reqParams)throws Exception{
        request.setAttribute("reqParams", !StringUtil.isEmpty(reqParams) ? new String(reqParams.getBytes("iso-8859-1"), "gbk") : "");
        request.setAttribute("team", findYxTeamByIdService.find(id));
        return "youxue/team/editQq";
    }

    /**
     * @param request
     * @return
     */
    @RequestMapping(value = "editQq")
    @ResponseBody
    public JSONObject editQq(HttpServletRequest request, long id, String qq) throws Exception {
        JSONObject jsonObject = new JSONObject();
        editYxTeamService.editQq(id, qq, UserUtil.getLoginUserForLoginId(request), UserUtil.getLoginUserForName(request));
        jsonObject.put("state", 0);
        return jsonObject;
    }
}
