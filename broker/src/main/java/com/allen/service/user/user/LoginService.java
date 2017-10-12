package com.allen.service.user.user;

import com.allen.entity.user.User;

/**
 * Created by Allen on 2017/6/26.
 */
public interface LoginService {
    public User login(String loginName, String pwd)throws Exception;
}
