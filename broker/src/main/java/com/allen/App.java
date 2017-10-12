package com.allen;

import com.allen.base.config.ConfigProp;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.boot.web.servlet.ServletComponentScan;

/**
 * Created by Allen on 2016/12/22 0022.
 */
@ServletComponentScan
@SpringBootApplication
@EnableConfigurationProperties({ConfigProp.class})
public class App extends SpringBootServletInitializer {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
