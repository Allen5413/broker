package com.allen.dao.user.user;

import com.allen.dao.BaseQueryDao;
import com.allen.dao.PageInfo;
import com.allen.util.StringUtil;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Allen on 2017/7/4.
 */
@Service
public class FindUserDao extends BaseQueryDao {
    public PageInfo findPage(PageInfo pageInfo, Map<String, String> paramsMap, String orderBy)throws Exception{
        List<Object> paramsList = new ArrayList<Object>();
        String centerId = paramsMap.get("centerId");
        String type = paramsMap.get("type");
        String state = paramsMap.get("state");
        String name = paramsMap.get("name");

        String fileds = "u.*, ug.`name` ugName";
        String sql = "from user u LEFT JOIN user_group_user ugu on u.id = ugu.user_id ";
        sql += "LEFT JOIN user_group ug on ugu.user_group_id = ug.id ";
        sql += "where u.state > 0 and u.center_id = ? ";
        paramsList.add(Long.parseLong(centerId));
        if(!StringUtil.isEmpty(type)){
            sql += "and u.type = ? ";
            paramsList.add(Integer.parseInt(type));
        }
        if(!StringUtil.isEmpty(state)){
            sql += "and u.state = ? ";
            paramsList.add(Integer.parseInt(state));
        }
        if(!StringUtil.isEmpty(name)){
            sql += "and u.name like ? ";
            paramsList.add("%"+name+"%");
        }
        sql += "order by "+orderBy;

        return super.pageSqlQueryByNativeSqlToMap(pageInfo, sql, fileds, paramsList.toArray());
    }

    /**
     * 查询一个学习中心下的通讯录，只要就是中心子账户和管理员
     * @param centerId
     * @return
     * @throws Exception
     */
    public List<Map> findByCenterIdForCenterMan(Long centerId)throws Exception{
        Map<String, Object> paramMaps = new HashMap<String, Object>();
        paramMaps.put("u.center_id", centerId);
        Map<String, Boolean> sortMaps = new HashMap<String, Boolean>();
        sortMaps.put("u.type", true);
        String fields = "u.id, u.name, u.phone, ug.name ugName";
        String[] tableNames = {"user u", "user_group_user ugu", "user_group ug"};
        String defaultWhere = "u.id = ugu.user_id and ugu.user_group_id = ug.id";
        return super.findListBySqlToMap(tableNames, fields, defaultWhere, paramMaps, sortMaps);
    }

    /**
     * 统计一个学习中心下的各个分销商的招生人数统计
     * @return
     * @throws Exception
     */
    public PageInfo findFxsStudentNumForByCenterIdPage(PageInfo pageInfo, Map<String, Object> paramsMap, Map<String, Boolean> sortMap)throws Exception{
        String fields = "u.id, u.name, u.phone, sc.id scId, sc.name scName, rt.id rtId, rt.name rtName, l.id lId, l.name lName, sp.id spId, sp.name spName, tp.id tpId, tp.year, tp.term, count(*) num";
        String[] tableNames = {"user u", "student s", "school sc", "recruit_type rt", "level l", "spec sp", "teach_plan tp"};
        String defaultWhere = "u.id = s.user_id and u.id != -1 and s.school_id = sc.id and s.recruit_type_id = rt.id and s.level_id = l.id and s.spec_id = sp.id and s.teach_plan_id = tp.id";
        String groupBy = "u.id, u.name, u.phone, sc.id, sc.name, rt.id, rt.name, l.id, l.name, sp.id, sp.name, tp.id, tp.year, tp.term";
        return super.findPageByNativeSqlToMap(pageInfo, fields, defaultWhere, tableNames, groupBy, paramsMap, sortMap);
    }
}
