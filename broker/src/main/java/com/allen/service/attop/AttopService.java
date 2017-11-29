package com.allen.service.attop;

import com.alibaba.fastjson.JSONObject;

import java.util.List;

/**
 * Created by Allen on 2017/10/12.
 */
public interface AttopService {
    /**
     * 发送消息
     * @param zz
     * @param msg
     * @throws Exception
     */
    public void sendMsg(String zz, String msg)throws Exception;

    /**
     * 通过zz，查询用户信息
     * @param zz
     * @param name
     * @return
     * @throws Exception
     */
    public JSONObject findZzInfo(String zz, String name)throws Exception;

    /**
     * 用户登录
     * @param loginName
     * @param pwd
     * @return
     * @throws Exception
     */
    public String login(String loginName, String pwd)throws Exception;

    /**
     * 获取所有高校信息
     * @return
     * @throws Exception
     */
    public List<JSONObject> findSchoolAll()throws Exception;
}
