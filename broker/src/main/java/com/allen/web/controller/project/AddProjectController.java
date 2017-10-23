package com.allen.web.controller.project;

import com.alibaba.fastjson.JSONObject;
import com.allen.base.config.ConfigProp;
import com.allen.entity.project.Project;
import com.allen.service.project.AddProjectService;
import com.allen.util.StringUtil;
import com.allen.util.UpLoadFileUtil;
import com.allen.util.UserUtil;
import com.allen.web.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.UUID;

/**
 * Created by Allen on 2017/6/28.
 */
@Controller
@RequestMapping(value = "/addProject")
public class AddProjectController extends BaseController {

    @Autowired
    private AddProjectService addProjectService;
    @Autowired
    private ConfigProp configProp;

    /**
     * @return
     */
    @RequestMapping(value = "open")
    public String open(HttpServletRequest request, @RequestParam(value = "reqParams", required = false)String reqParams)throws Exception{
        request.setAttribute("reqParams", !StringUtil.isEmpty(reqParams) ? new String(reqParams.getBytes("iso-8859-1"), "gbk") : "");
        return "project/add";
    }

    /**
     * @param request
     * @return
     */
    @RequestMapping(value = "add")
    @ResponseBody
    public JSONObject add(HttpServletRequest request, Project project, String domain,
                          @RequestParam(value = "fileName", required = false)String fileName) throws Exception {
        JSONObject jsonObject = new JSONObject();
        if(null != project) {
            project.setCreator(UserUtil.getLoginUserForName(request));
            project.setOperator(UserUtil.getLoginUserForName(request));
            addProjectService.add(project, request, fileName, domain);
        }
        jsonObject.put("state", 0);
        return jsonObject;
    }

    @RequestMapping(value = "upload")
    @ResponseBody
    public JSONObject uploadFile(HttpServletRequest request, @RequestParam("file") List<MultipartFile> uploadFileList)throws Exception{
        JSONObject jsonObject = new JSONObject();
        String fileName = UUID.randomUUID().toString();
        String path = UpLoadFileUtil.uploadImg(request, uploadFileList, "jpg|JPG|png|PNG|bmp|BMP|jpge|JPGE", 1024 * 1, 5, configProp.getUpload().get("tempPath"), fileName);
        jsonObject.put("state", 0);
        jsonObject.put("path", path);
        jsonObject.put("fileName", fileName+path.substring(path.lastIndexOf("."), path.length()));
        return jsonObject;
    }
}
