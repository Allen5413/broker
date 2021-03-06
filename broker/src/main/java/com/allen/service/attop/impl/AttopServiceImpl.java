package com.allen.service.attop.impl;

import com.alibaba.fastjson.JSONObject;
import com.allen.base.config.ConfigProp;
import com.allen.service.attop.AttopService;
import com.allen.util.AttopUtil;
import com.allen.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Allen on 2017/10/12.
 */
@Service
public class AttopServiceImpl implements AttopService {

    @Autowired
    private ConfigProp configProp;

    @Override
    public void sendMsg(String zz, String msg) throws Exception {
        String shopId = configProp.getAttop().get("shopId");
        String secret = configProp.getAttop().get("secret");
        String portUrl = configProp.getAttop().get("portUrl");
        AttopUtil.getPortMsg(201, "zz=" + zz + "&msg=" + AttopUtil.toUrlEncode(msg), shopId, secret, portUrl);
    }

    @Override
    public JSONObject findZzInfo(String zz, String name) throws Exception {
        String shopId = configProp.getAttop().get("shopId");
        String secret = configProp.getAttop().get("secret");
        String portUrl = configProp.getAttop().get("portUrl");
        return AttopUtil.getPortMsg(200, "zz=" + zz + "&name=" + AttopUtil.toUrlEncode(StringUtil.isEmpty(name) ? "" : name), shopId, secret, portUrl);
    }

    @Override
    public String login(String loginName, String pwd) throws Exception {
        String shopId = configProp.getAttop().get("shopId");
        String secret = configProp.getAttop().get("secret");
        String portUrl = configProp.getAttop().get("portUrl");
        JSONObject json = AttopUtil.getPortMsg(202, "username=" + AttopUtil.toUrlEncode(loginName) + "&password=" + AttopUtil.getMd5(pwd), shopId, secret, portUrl);
        if(json.get("status").toString().equals("1")){
            return json.get("zz").toString();
        }else{
            return "";
        }
    }

    @Override
    public List<JSONObject> findSchoolAll() throws Exception {
        List<JSONObject> list = null;
        String shopId = configProp.getAttop().get("shopId");
        String secret = configProp.getAttop().get("secret");
        String portUrl = configProp.getAttop().get("portUrl");
        JSONObject json = AttopUtil.getPortMsg(203, "", shopId, secret, portUrl);
        if(null != json){
            list = (List<JSONObject>) json.get("data");
        }
        return list;
    }

    @Override
    public int findSchoolActiveByNo(String no) throws Exception {
        String shopId = configProp.getAttop().get("shopId");
        String secret = configProp.getAttop().get("secret");
        String portUrl = configProp.getAttop().get("portUrl");
        JSONObject json = AttopUtil.getPortMsg(204, "no=" + no, shopId, secret, portUrl);
        if(null != json){
            return null == json.get("num") ? 0 : Integer.parseInt(json.get("num").toString());
        }
        return 0;
    }
}
