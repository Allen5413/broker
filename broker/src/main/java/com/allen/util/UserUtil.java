package com.allen.util;

import javax.servlet.http.HttpServletRequest;

/**
 * 获取登录用户相关信息
 * Created by Allen on 2015/4/27.
 */
public class UserUtil {

    /**
     * 获取登录用户ID
     * @param request
     * @return
     */
    public static long getLoginUserForLoginId(HttpServletRequest request){
        if(null != request.getSession().getAttribute("userId")){
            return Long.parseLong(request.getSession().getAttribute("userId").toString());
        }else{
            return -1;
        }
    }

    /**
     * 获取登录用户的登录名
     * @param request
     * @return
     */
    public static String getLoginUserForLoginName(HttpServletRequest request){
        if(null != request.getSession().getAttribute("loginName")){
            return request.getSession().getAttribute("loginName").toString();
        }else{
            return "";
        }
    }

    /**
     * 获取登录用户的用户姓名
     * @param request
     * @return
     */
    public static String getLoginUserForName(HttpServletRequest request){
        if(null != request.getSession().getAttribute("name")){
            return request.getSession().getAttribute("name").toString();
        }else{
            return "";
        }
    }

    /**
     * 获取登录用户的类型
     * @param request
     * @return
     */
    public static Integer getLoginUserForType(HttpServletRequest request){
        return (Integer) request.getSession().getAttribute("type");
    }

    /**
     * 获取登录用户所在项目
     * @param request
     * @return
     */
    public static Long getLoginUserForProjectId(HttpServletRequest request){
        return (Long) request.getSession().getAttribute("projectId");
    }

    /**
     * 获取登录用户的操作后是否需要中心管理员审核
     * @param request
     * @return
     */
    public static Integer getLoginUserForIsOperateAudit(HttpServletRequest request){
        return (Integer) request.getSession().getAttribute("isOperateAudit");
    }
}
