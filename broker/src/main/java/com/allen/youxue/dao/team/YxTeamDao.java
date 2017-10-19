package com.allen.youxue.dao.team;

import com.allen.youxue.entity.team.Team;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.math.BigInteger;
import java.util.List;

/**
 * Created by Allen on 2017/10/17 0017.
 */
public interface YxTeamDao extends CrudRepository<Team, Long> {
    public Team findByZzAndProductDateId(String zz, long productDateId)throws Exception;
    public List<Team> findByIsHead(int isHead)throws Exception;
    public Team findByZzAndParentId(String zz, long parentId)throws Exception;

    /**
     * 统计一个团的团员人数
     * @param parentId
     * @return
     * @throws Exception
     */
    @Query(nativeQuery = true, value = "select count(*) from yx_team where parent_id = ?1")
    public BigInteger countTeamNum(long parentId)throws Exception;
}
