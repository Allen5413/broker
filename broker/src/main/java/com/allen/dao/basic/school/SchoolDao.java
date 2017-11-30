package com.allen.dao.basic.school;

import com.allen.entity.basic.School;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by Allen on 2017/11/28.
 */
public interface SchoolDao extends CrudRepository<School, Long> {

    public School findByNo(String no)throws Exception;

    /**
     * 查询学生人数大于0的学校
     * @return
     * @throws Exception
     */
    @Query("from School where num > 0")
    public List<School> findForHaveNum()throws Exception;
}
