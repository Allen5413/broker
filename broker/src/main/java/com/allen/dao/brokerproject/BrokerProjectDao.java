package com.allen.dao.brokerproject;

import com.allen.entity.brokerproject.BrokerProject;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by Allen on 2017/10/10.
 */
public interface BrokerProjectDao extends CrudRepository<BrokerProject, Long> {
    public List<BrokerProject> findByProjectId(long projectId)throws Exception;
    public List<BrokerProject> findByProjectIdAndState(long projectId, int state)throws Exception;
    public BrokerProject findByBrokerIdAndProjectId(long brokerId, long projectId)throws Exception;

    @Query(nativeQuery = true, value = "select GROUP_CONCAT(t.zz) zz, t.ratio from " +
            "(" +
            "SELECT DISTINCT bp.creator zz, p.ratio " +
            "FROM broker_project bp, project p where bp.project_id = p.id and state = 1 and p.id = ?1 " +
            ") t")
    public List<Object[]> findForSchool(long projectId)throws Exception;

    /**
     * 查询一个项目下的一个下标的经纪人和他下面的成员数量
     * @param projectId
     * @param index
     * @return
     * @throws Exception
     */
    @Query(nativeQuery = true, value = "select t.creator, count(*) from " +
            "(" +
            "SELECT * FROM broker_project where project_id = ?1 limit ?2, 1" +
            ") t, customer c where t.broker_id = c.broker_id and t.project_id = c.project_id")
    public List<Object[]> findBrokerAndCustomerNumByProjectIdAndIndex(long projectId, int index)throws Exception;

    /**
     * 删除一个经纪人关联的所有项目
     * @param brokerId
     * @throws Exception
     */
    @Modifying
    @Query(nativeQuery = true, value = "delete from broker_project where broker_id = ?1")
    public void delByBrokerId(long brokerId)throws Exception;

    /**
     * 删除一个项目下的经纪人
     * @param projectId
     * @throws Exception
     */
    @Modifying
    @Query(nativeQuery = true, value = "delete from broker_project where project_id = ?1")
    public void delByProjectId(long projectId)throws Exception;
}
