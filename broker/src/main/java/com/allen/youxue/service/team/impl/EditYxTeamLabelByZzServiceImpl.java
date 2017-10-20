package com.allen.youxue.service.team.impl;

import com.allen.youxue.dao.team.YxTeamDao;
import com.allen.youxue.service.team.EditYxTeamLabelByZzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Allen on 2017/10/20.
 */
@Service
public class EditYxTeamLabelByZzServiceImpl implements EditYxTeamLabelByZzService {

    @Autowired
    private YxTeamDao yxTeamDao;
    @Override
    @Transactional
    public void edit(String label, String zz) throws Exception {
        yxTeamDao.editLabelByZz(label, zz);
    }
}
