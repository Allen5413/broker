package com.allen.youxue.service.teamimg;

import java.util.List;
import java.util.Map;

/**
 * Created by Allen on 2017/10/20.
 */
public interface FindYxTeamImgByZzService {
    public Map<String, List<String>> findImgByZz(String zz)throws Exception;
}
