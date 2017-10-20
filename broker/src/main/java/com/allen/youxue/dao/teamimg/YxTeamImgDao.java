package com.allen.youxue.dao.teamimg;

import com.allen.youxue.entity.team.TeamImg;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by Allen on 2017/10/20.
 */
public interface YxTeamImgDao extends CrudRepository<TeamImg, Long> {
    public List<TeamImg> findByZz(String zz)throws Exception;
}
