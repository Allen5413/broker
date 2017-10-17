package com.allen.service.basic.resource;

import com.allen.entity.basic.Resource;

import java.util.List;

/**
 * Created by Allen on 2017/6/26.
 */
public interface FindResourceByUserIdService {
    public List<Resource> findAdmin(long userId)throws Exception;

    public List<Resource> findProjectAdmin(long userId, long projectId)throws Exception;
}
