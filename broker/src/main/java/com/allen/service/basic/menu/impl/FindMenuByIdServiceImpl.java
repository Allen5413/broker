package com.allen.service.basic.menu.impl;

import com.allen.dao.basic.menu.MenuDao;
import com.allen.entity.basic.Menu;
import com.allen.service.basic.menu.FindMenuByIdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Allen on 2017/6/26.
 */
@Service
public class FindMenuByIdServiceImpl implements FindMenuByIdService {

    @Autowired
    private MenuDao menuDao;

    @Override
    public Menu find(long id) throws Exception {
        return menuDao.findOne(id);
    }
}
