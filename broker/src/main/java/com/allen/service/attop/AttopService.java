package com.allen.service.attop;

import com.alibaba.fastjson.JSONObject;

/**
 * Created by Allen on 2017/10/12.
 */
public interface AttopService {
    public void sendMsg(String zz, String msg)throws Exception;
    public JSONObject findZzInfo(String zz, String name)throws Exception;
    public String login(String loginName, String pwd)throws Exception;
}
