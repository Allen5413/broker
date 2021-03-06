package com.allen.dao.chief;

import com.allen.entity.chief.Chief;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by Allen on 2017/10/10.
 */
public interface ChiefDao extends CrudRepository<Chief, Long> {
    public List<Chief> findByZz(String zz)throws Exception;
    public Chief findBySchoolNo(String schoolNo)throws Exception;
}
