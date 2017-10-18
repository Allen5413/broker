package com.allen.youxue.dao.team;

import com.allen.youxue.entity.team.Team;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by Allen on 2017/10/17 0017.
 */
public interface YxTeamDao extends CrudRepository<Team, Long> {
    public Team findByZzAndProductDateId(String zz, long productDateId)throws Exception;
    public List<Team> findByIsHead(int isHead)throws Exception;
}
