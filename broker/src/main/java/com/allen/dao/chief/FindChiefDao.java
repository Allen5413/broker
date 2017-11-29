package com.allen.dao.chief;

import com.allen.dao.BaseQueryDao;
import com.allen.dao.PageInfo;
import com.allen.util.StringUtil;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Allen on 2017/11/28.
 */
@Service
public class FindChiefDao extends BaseQueryDao {
    public PageInfo findPage(PageInfo pageInfo, Map<String, String> paramsMap)throws Exception{
        List<Object> paramsList = new ArrayList<Object>();
        String name = paramsMap.get("name");
        String type = paramsMap.get("type");
        String provCode = paramsMap.get("provCode");
        String cityCode = paramsMap.get("cityCode");
        String areaCode = paramsMap.get("areaCode");

        String fileds = "s.id, s.no, s.name, c.zz, rm.zz rZz";
        String sql = "from school s LEFT JOIN chief c on s.no = c.school_no ";
        sql += "LEFT JOIN recommend_man rm on c.recommend_zz = rm.zz ";
        sql += "where 1=1 ";
        if(!StringUtil.isEmpty(type)){
            sql += "and s.type = ? ";
            paramsList.add(type);
        }
        if(!StringUtil.isEmpty(name)){
            sql += "and s.name like ? ";
            paramsList.add("%"+name+"%");
        }
        if(!StringUtil.isEmpty(areaCode)){
            sql += "and s.area_code = ? ";
            paramsList.add(areaCode);
        }else{
            if(!StringUtil.isEmpty(cityCode)){
                sql += "and s.city_code = ? ";
                paramsList.add(cityCode);
            }else{
                if(!StringUtil.isEmpty(provCode)) {
                    sql += "and s.prov_code = ? ";
                    paramsList.add(provCode);
                }
            }
        }
        return super.pageSqlQueryByNativeSqlToMap(pageInfo, sql, fileds, paramsList.toArray());
    }
}
