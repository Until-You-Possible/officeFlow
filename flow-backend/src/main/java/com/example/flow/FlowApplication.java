package com.example.flow;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})
public class FlowApplication {

    public static void main(String[] args) {
        SpringApplication.run(FlowApplication.class, args);
    }

}
