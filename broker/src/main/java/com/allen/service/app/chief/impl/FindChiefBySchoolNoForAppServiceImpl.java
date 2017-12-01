package com.allen.service.app.chief.impl;

import com.alibaba.fastjson.JSONObject;
import com.allen.base.exception.BusinessException;
import com.allen.dao.chief.ChiefDao;
import com.allen.entity.chief.Chief;
import com.allen.service.app.chief.FindChiefBySchoolNoForAppService;
import com.allen.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Allen on 2017/11/27.
 */
@Service
public class FindChiefBySchoolNoForAppServiceImpl implements FindChiefBySchoolNoForAppService {

    @Autowired
    private ChiefDao chiefDao;

    @Override
    public JSONObject find(HttpServletRequest request) throws Exception {
        JSONObject jsonObject = new JSONObject();
        String no = request.getParameter("no");
        if(StringUtil.isEmpty(no)){
            throw new BusinessException("没有传入学校No");
        }
        Chief chief = chiefDao.findBySchoolNo(no);
        jsonObject.put("zz", null != chief ? chief.getZz() : "0");
        jsonObject.put("status", 1);
        return jsonObject;
    }
}
