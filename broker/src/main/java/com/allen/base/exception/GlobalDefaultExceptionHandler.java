package com.allen.base.exception;

import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * 全局异常
 * Created by Allen on 2016/12/7.
 */
@ControllerAdvice
public class GlobalDefaultExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public JSONObject defaultErrorHandler(HttpServletRequest req, Exception e)  {
        JSONObject jsonObject = new JSONObject();
        //打印异常信息：
        e.printStackTrace();

        String eMsg = e.getMessage();
        if(-1 < eMsg.indexOf("The field img exceeds its maximum permitted size")){
            eMsg = "上传图片不能超过1m";
        }else{
            eMsg = "程序出现了异常";
        }
        jsonObject.put("state", 1);
        jsonObject.put("msg", eMsg);
        //主要是用于app接口用的，朱总非要把返回字段定义成他那种格式的，没得法
        jsonObject.put("status", 0);
        jsonObject.put("err", "程序出现了异常");
        return jsonObject;
    }

    @ExceptionHandler(value = BusinessException.class)
    @ResponseBody
    public JSONObject businessErrorHandler(HttpServletRequest req, Exception e)  {
        JSONObject jsonObject = new JSONObject();
        //打印异常信息：
        e.printStackTrace();

        String eMsg = e.getMessage();
        if(-1 < eMsg.indexOf("StaleObjectStateException")){
            eMsg = "您操作的数据已经被修改，请重新获取最新的数据再做操作！";
        }
        jsonObject.put("state", 1);
        jsonObject.put("msg", eMsg);
        //主要是用于app接口用的，朱总非要把返回字段定义成他那种格式的，没得法
        jsonObject.put("status", 0);
        jsonObject.put("err", eMsg);
        return jsonObject;
    }
}
