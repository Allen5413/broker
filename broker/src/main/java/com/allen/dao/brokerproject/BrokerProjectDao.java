package com.allen.dao.brokerproject;

import com.allen.entity.brokerproject.BrokerProject;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by Allen on 2017/10/10.
 */
public interface BrokerProjectDao extends CrudRepository<BrokerProject, Long> {
    public List<BrokerProject> findByProjectId(long projectId)throws Exception;

    @Query(nativeQuery = true, value = "select GROUP_CONCAT(t.zz) zz, t.ratio from " +
            "(" +
            "SELECT DISTINCT bp.creator zz, p.ratio " +
            "FROM broker_project bp, project p where bp.project_id = p.id and state = 1 and p.id = ?1 " +
            ") t")
    public List<Object[]> findForSchool(long projectId)throws Exception;
}
