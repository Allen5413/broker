package com.allen.service.basic.resource.impl;

import com.allen.dao.basic.resource.ResourceDao;
import com.allen.entity.basic.Resource;
import com.allen.service.basic.resource.FindResourceByUserIdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Allen on 2017/6/26.
 */
@Service
public class FindResourceByUserIdServiceImpl implements FindResourceByUserIdService {

    @Autowired
    private ResourceDao resourceDao;

    @Override
    public List<Resource> findAdmin(long userId) throws Exception {
        return resourceDao.findByUserId(userId);
    }

    @Override
    public List<Resource> findProjectAdmin(long userId, long projectId) throws Exception {
        return resourceDao.findByUserId(userId, projectId);
    }
}
