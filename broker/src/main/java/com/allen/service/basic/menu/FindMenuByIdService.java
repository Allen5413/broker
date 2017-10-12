package com.allen.service.basic.menu;

import com.allen.entity.basic.Menu;

/**
 * Created by Allen on 2017/6/26.
 */
public interface FindMenuByIdService {
    public Menu find(long id)throws Exception;
}
