package com.allen.service.chief;

import com.allen.entity.chief.Chief;

/**
 * Created by Allen on 2017/11/30.
 */
public interface FindChiefByNoService {
    public Chief find(String no)throws Exception;
}
