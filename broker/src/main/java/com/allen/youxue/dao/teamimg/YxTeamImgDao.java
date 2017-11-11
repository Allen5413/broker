package com.allen.youxue.dao.teamimg;

import com.allen.youxue.entity.team.TeamImg;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by Allen on 2017/10/20.
 */
public interface YxTeamImgDao extends CrudRepository<TeamImg, Long> {
    public List<TeamImg> findByZz(String zz)throws Exception;
    public List<TeamImg> findByZzAndIsCover(String zz, int isCover)throws Exception;

    /**
     * 查询一个客户上传的最新的一张照片
     * @param zz
     * @return
     * @throws Exception
     */
    @Query(nativeQuery = true, value = "select t.img_url from yx_team_img t where t.zz = ?1 and t.is_cover = 0 order by t.create_time desc limit 1")
    public String findByZzForNewImg(String zz)throws Exception;

    /**
     * 查询一个客户上传的封面照
     * @param zz
     * @return
     * @throws Exception
     */
    @Query(nativeQuery = true, value = "select t.img_url from yx_team_img t where t.zz = ?1 and t.is_cover = 1")
    public String findByZzForCover(String zz)throws Exception;

    /**
     * 删除成员的一个照片
     * @param imgUrl
     * @throws Exception
     */
    @Modifying
    @Query(nativeQuery = true, value = "delete from yx_team_img where img_url = ?1")
    public void delByImgUrl(String imgUrl)throws Exception;
}
