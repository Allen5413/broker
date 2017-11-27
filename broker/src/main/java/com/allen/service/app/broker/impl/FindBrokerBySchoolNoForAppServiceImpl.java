package com.allen.service.app.broker.impl;

import com.alibaba.fastjson.JSONObject;
import com.allen.base.exception.BusinessException;
import com.allen.dao.PageInfo;
import com.allen.dao.broker.FindBrokerDao;
import com.allen.entity.broker.Broker;
import com.allen.service.app.broker.FindBrokerBySchoolNoForAppService;
import com.allen.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Allen on 2017/11/27.
 */
@Service
public class FindBrokerBySchoolNoForAppServiceImpl implements FindBrokerBySchoolNoForAppService {

    @Autowired
    private FindBrokerDao findBrokerDao;

    @Override
    public JSONObject find(HttpServletRequest request) throws Exception {
        JSONObject jsonObject = new JSONObject();
        String no = request.getParameter("no");
        String num = request.getParameter("num");
        if(StringUtil.isEmpty(no)){
            throw new BusinessException("没有传入学校No");
        }
        if(StringUtil.isEmpty(num)){
            num = "10";
        }

        PageInfo pageInfo = new PageInfo();
        pageInfo.setCurrentPage(1);
        pageInfo.setCountOfCurrentPage(Integer.parseInt(num));
        Map<String, Object> paramsMap = new HashMap<String, Object>(1);
        paramsMap.put("b.schoolNo", no);
        paramsMap.put("b.isBlack", Broker.ISBLACK_NOT);
        pageInfo = findBrokerDao.findPage(pageInfo, paramsMap, null);
        String[] zzArray = new String[]{};
        if(null != pageInfo && null != pageInfo.getPageResults() && 0 < pageInfo.getPageResults().size()) {
            List<Broker> brokerList = pageInfo.getPageResults();
            zzArray = new String[brokerList.size()];
            int i = 0;
            for(Broker broker : brokerList){
                zzArray[i] = broker.getZz();
                i++;
            }

        }
        jsonObject.put("list", zzArray);
        jsonObject.put("status", 1);
        return jsonObject;
    }
}
