package com.allen.web.controller.project;

import com.alibaba.fastjson.JSONObject;
import com.allen.entity.project.Project;
import com.allen.service.project.EditProjectService;
import com.allen.service.project.FindProjectByIdService;
import com.allen.util.StringUtil;
import com.allen.util.UserUtil;
import com.allen.web.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Allen on 2017/6/28.
 */
@Controller
@RequestMapping(value = "/editProject")
public class EditProjectController extends BaseController {

    @Autowired
    private EditProjectService editProjectService;
    @Autowired
    private FindProjectByIdService findProjectByIdService;

    /**
     * @return
     */
    @RequestMapping(value = "open")
    public String open(HttpServletRequest request, Long id,
                       @RequestParam(value = "reqParams", required = false)String reqParams)throws Exception{
        request.setAttribute("project", findProjectByIdService.find(id));
        request.setAttribute("reqParams", !StringUtil.isEmpty(reqParams) ? new String(reqParams.getBytes("iso-8859-1"), "gbk") : "");
        return "project/edit";
    }

    /**
     * @param request
     * @return
     */
    @RequestMapping(value = "editor")
    @ResponseBody
    public JSONObject editor(HttpServletRequest request, Project project) throws Exception {
        JSONObject jsonObject = new JSONObject();
        if(null != project) {
            project.setOperator(UserUtil.getLoginUserForName(request));
            editProjectService.add(project, UserUtil.getLoginUserForLoginId(request));
        }
        jsonObject.put("state", 0);
        return jsonObject;
    }
}
