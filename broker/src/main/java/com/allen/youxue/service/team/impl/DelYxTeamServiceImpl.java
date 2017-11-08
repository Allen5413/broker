package com.allen.youxue.service.team.impl;

import com.allen.base.exception.BusinessException;
import com.allen.youxue.dao.team.YxTeamDao;
import com.allen.youxue.entity.team.Team;
import com.allen.youxue.service.team.DelYxTeamService;
import com.allen.youxue.service.teamimg.DelYxTeamImgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Allen on 2017/11/8.
 */
@Service
public class DelYxTeamServiceImpl implements DelYxTeamService {

    @Autowired
    private YxTeamDao yxTeamDao;
    @Autowired
    private DelYxTeamImgService delYxTeamImgService;

    @Override
    public void del(HttpServletRequest request, long id) throws Exception {
        Team team = yxTeamDao.findOne(id);
        if(null != team) {
            //如果是团长，检查下面是否有团员，有的话就不能删除
            if(Team.ISHEAD_YES == team.getIsHead()) {
                List<Team> teamList = yxTeamDao.findByParentId(id);
                if(null != teamList && 0 < teamList.size()){
                    throw new BusinessException("该团长下已有团员报名，不能被删除");
                }
                //这里应该还要检查该团长zz号，是否再其他项目还有关联，如果有就不删照片。但是现在只有一个游学项目，所以就不验证了
                //删掉团长上传的
                delYxTeamImgService.delByZz(request, team.getZz());
            }
            yxTeamDao.delete(id);
        }
    }
}
