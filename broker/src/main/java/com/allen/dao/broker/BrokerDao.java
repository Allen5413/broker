package com.allen.dao.broker;

import com.allen.entity.broker.Broker;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Allen on 2017/10/10.
 */
public interface BrokerDao extends CrudRepository<Broker, Long> {
    public Broker findByZz(String zz)throws Exception;
}
