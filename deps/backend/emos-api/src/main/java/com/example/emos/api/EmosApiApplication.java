package com.example.emos.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@ServletComponentScan
@Slf4j
@EnableAsync
public class EmosApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(EmosApiApplication.class, args);

    }
}