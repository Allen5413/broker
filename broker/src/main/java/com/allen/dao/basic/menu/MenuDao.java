package com.allen.dao.basic.menu;

import com.allen.entity.basic.Menu;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Allen on 2017/6/26.
 */
public interface MenuDao extends CrudRepository<Menu, Long> {
}
