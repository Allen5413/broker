package com.allen.youxue.web.controller.app;

import com.alibaba.fastjson.JSONObject;
import com.allen.base.config.ConfigProp;
import com.allen.base.exception.BusinessException;
import com.allen.util.UpLoadFileUtil;
import com.allen.util.UserUtil;
import com.allen.web.controller.BaseController;
import com.allen.youxue.service.teamimg.AddYxTeamImgService;
import com.allen.youxue.service.teamimg.FindYxTeamImgByZzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Created by Allen on 2017/10/20.
 */
@Controller
@RequestMapping(value = "/youxueApp/uploadMyImg")
public class UploadYxMyImgController extends BaseController {

    @Autowired
    private ConfigProp configProp;
    @Autowired
    private AddYxTeamImgService addYxTeamImgService;
    @Autowired
    private FindYxTeamImgByZzService findYxTeamImgByZzService;

    @RequestMapping(value = "open")
    public String open(HttpServletRequest request) throws Exception {
        Map<String, List<String[]>> map = findYxTeamImgByZzService.findImgByZz(UserUtil.getLoginUserForLoginName(request));
        request.setAttribute("imgMap", map);
        return "/youxue/app/uploadMyImg";
    }

    @RequestMapping(value = "upload")
    @ResponseBody
    public JSONObject uploadFile(HttpServletRequest request, @RequestParam("file") List<MultipartFile> uploadFileList)throws Exception{
        JSONObject jsonObject = new JSONObject();
        String fileName = UUID.randomUUID().toString();
        String path = UpLoadFileUtil.uploadImg(request, uploadFileList, "jpg|JPG|png|PNG|bmp|BMP|jpge|JPGE", 1024*10, 5, configProp.getUpload().get("tempPath"), fileName);
        jsonObject.put("state", 0);
        jsonObject.put("path", path);
        jsonObject.put("fileName", fileName+path.substring(path.lastIndexOf("."), path.length()));
        return jsonObject;
    }

    @RequestMapping(value = "del")
    @ResponseBody
    public JSONObject delFile(HttpServletRequest request, String path)throws Exception{
        JSONObject jsonObject = new JSONObject();
        UpLoadFileUtil.delFile(request, path);
        jsonObject.put("state", 0);
        return jsonObject;
    }

    @RequestMapping(value = "addImg")
    @ResponseBody
    public JSONObject addImg(HttpServletRequest request, String fileNames, String domain)throws Exception{
        JSONObject jsonObject = new JSONObject();
        String[] fileNameArray = fileNames.split(",");
        if(null == fileNameArray || 1 > fileNameArray.length){
            throw new BusinessException("请选择要上传的照片");
        }
        addYxTeamImgService.add(UserUtil.getLoginUserForLoginName(request), domain, fileNameArray);
        for(String fileName : fileNameArray){
            UpLoadFileUtil.custAndThumbnailsFile(request, configProp.getUpload().get("tempPath") + fileName, configProp.getUpload().get("teamImgPath"), configProp.getUpload().get("teamSmallImgPath"), UserUtil.getLoginUserForLoginName(request) + "_"+fileName);
        }
        jsonObject.put("state", 0);
        return jsonObject;
    }
}
