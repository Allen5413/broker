package com.allen.dao.customerdaylogincount;

import com.allen.entity.broker.CustomerDayLoginCount;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by Allen on 2017/10/23.
 */
public interface CustomerDayLoginCountDao extends CrudRepository<CustomerDayLoginCount, Long> {
    public CustomerDayLoginCount findByZzAndProjectIdAndDate(String zz, long projectId, String date)throws Exception;

    @Query(nativeQuery = true, value = "select DATE_FORMAT(t.date, '%d'), IFNULL(c.count,0) count FROM " +
            "(" +
            "    SELECT curdate() as date " +
            "    union all " +
            "    SELECT date_sub(curdate(), interval 1 day) as click_date " +
            "    union all " +
            "    SELECT date_sub(curdate(), interval 2 day) as click_date " +
            "    union all " +
            "    SELECT date_sub(curdate(), interval 3 day) as click_date " +
            "    union all " +
            "    SELECT date_sub(curdate(), interval 4 day) as click_date " +
            "    union all " +
            "    SELECT date_sub(curdate(), interval 5 day) as click_date " +
            "    union all " +
            "    SELECT date_sub(curdate(), interval 6 day) as click_date " +
            ") t LEFT JOIN customer_day_login_count c on t.date = DATE_FORMAT(c.date,'%Y-%m-%d') and c.zz = ?1 and c.project_id = ?2 " +
            "order by t.date")
    public List<Object[]> find7DayByZzAndProjectId(String zz, long projectId)throws Exception;
}
