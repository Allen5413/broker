package com.allen.youxue.dao.teamimg;

import com.allen.dao.BaseQueryDao;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Allen on 2017/10/20.
 */
@Service
public class FindYxTeamImgDao extends BaseQueryDao {

    public List<Map> findByZz(String zz)throws Exception{
        String fields = "DATE_FORMAT(create_time, '%Y-%m-%d') date, img_url url";
        String[] tableNames = {"yx_team_img"};
        String defaultWhere = "1=1";
        Map<String, Object> paramsMap = new HashMap<String, Object>(1);
        paramsMap.put("zz", zz);
        Map<String, Boolean> sortMap = new HashMap<String, Boolean>(1);
        sortMap.put("create_time", false);
        return super.findListBySqlToMap(tableNames, fields, defaultWhere, paramsMap, sortMap);
    }
}
