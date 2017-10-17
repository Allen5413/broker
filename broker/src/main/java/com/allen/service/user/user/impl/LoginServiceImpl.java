package com.allen.service.user.user.impl;

import com.allen.base.exception.BusinessException;
import com.allen.dao.user.user.UserDao;
import com.allen.entity.user.User;
import com.allen.service.user.user.LoginService;
import com.allen.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Allen on 2017/6/26.
 */
@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private UserDao userDao;

    @Override
    public User login(String loginName) throws Exception {
        User user = userDao.findByLoginName(loginName);
        if(null != user){
            if(user.getState() == User.STATE_DELETE){
                throw new BusinessException("您的账号已被删除！");
            }
            if(user.getState() == User.STATE_DISABLE){
                throw new BusinessException("您的账号已被停用！");
            }
        }
        return user;
    }
}
