package com.allen.youxue.dao.teamimg;

import com.allen.youxue.entity.team.TeamImg;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by Allen on 2017/10/20.
 */
public interface YxTeamImgDao extends CrudRepository<TeamImg, Long> {
    public List<TeamImg> findByZz(String zz)throws Exception;

    /**
     * 查询一个客户上传的最新的一张照片
     * @param zz
     * @return
     * @throws Exception
     */
    @Query(nativeQuery = true, value = "select t.img_url from yx_team_img t where t.zz = ?1 order by t.create_time desc limit 1")
    public String findByZzForNewImg(String zz)throws Exception;
}
