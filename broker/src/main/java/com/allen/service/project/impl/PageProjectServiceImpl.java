package com.allen.service.project.impl;

import com.allen.dao.PageInfo;
import com.allen.dao.brokerproject.BrokerProjectDao;
import com.allen.dao.project.FindProjectDao;
import com.allen.entity.brokerproject.BrokerProject;
import com.allen.entity.project.Project;
import com.allen.service.project.PageProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Allen on 2017/10/10.
 */
@Service
public class PageProjectServiceImpl implements PageProjectService {

    @Autowired
    private FindProjectDao findProjectDao;
    @Autowired
    private BrokerProjectDao brokerProjectDao;

    @Override
    public PageInfo find(PageInfo pageInfo, Map<String, Object> paramsMap, Map<String, Boolean> sortMap) throws Exception {
        pageInfo = findProjectDao.findPage(pageInfo, paramsMap, sortMap);
        List list = pageInfo.getPageResults();
        if(null != list && 0 < list.size()){
            List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>(list.size());
            for(int i=0; i<list.size(); i++){
                Map<String, Object> map = new HashMap<String, Object>();
                Project project = (Project) list.get(i);
                map.put("id", project.getId());
                map.put("name", project.getName());
                map.put("auditType", project.getAuditType());
                map.put("auditTypeStr", Project.AUDITTYPE_AUTO == project.getAuditType() ? "自动":"手动");
                map.put("frequency", project.getFrequency());
                map.put("ratio", project.getRatio());
                //查询经纪人数量
                List<BrokerProject> brokerProjectList = brokerProjectDao.findByProjectId(project.getId());
                map.put("brokerCount", null == brokerProjectList ? 0 : brokerProjectList.size());
                //查询经纪人学校覆盖数量
                map.put("schoolCount", 0);
                resultList.add(map);
            }
            pageInfo.setPageResults(resultList);
        }
        return pageInfo;
    }
}
