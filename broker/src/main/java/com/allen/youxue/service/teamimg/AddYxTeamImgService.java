package com.allen.youxue.service.teamimg;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Allen on 2017/10/20.
 */
public interface AddYxTeamImgService {
    public void add(String zz, String domain, String... fileNames)throws Exception;

    public void addCover(HttpServletRequest request, String zz, String domain, String fileName)throws Exception;
}
