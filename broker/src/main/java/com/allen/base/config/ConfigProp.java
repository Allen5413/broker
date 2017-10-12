package com.allen.base.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Allen on 2017/6/5.
 */
@ConfigurationProperties(prefix = "broker", locations = "classpath:config.properties")
public class ConfigProp {
    public Map<String, String> attop = new HashMap<String, String>();

    public Map<String, String> getAttop() {
        return attop;
    }

    public void setAttop(Map<String, String> attop) {
        this.attop = attop;
    }
}
