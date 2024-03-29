package com.example.flow.core.configuration;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;


/**
 *
 */
@ConfigurationProperties(prefix = "flow")
@Component
@PropertySource(value = "classpath:config/exception-code.properties")
public class ExceptionCodeConfiguration {

    private final Map<Integer, String> codes = new HashMap<>();

    public Map<Integer, String> getCodes() {
        return codes;
    }

    public String getMessage(int code) {
        return codes.get(code);
    }
}


// 对 PropertySource设置  encoding = "UTF-8"
//  如果是对于springboot默认生成的application.properties是没有效果的