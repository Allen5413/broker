package com.allen.youxue.service.teamimg;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Allen on 2017/11/8.
 */
public interface DelYxTeamImgService {
    public void del(HttpServletRequest request, String path, String smallPath)throws Exception;

    public void delByZz(HttpServletRequest request, String zz)throws Exception;
}
