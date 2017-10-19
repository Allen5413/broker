package com.allen.youxue.service.product;

import java.util.List;
import java.util.Map;

/**
 * Created by Allen on 2017/10/19.
 */
public interface FindYxProductForAppService {
    public List<Map> find(long projectId)throws Exception;
}
