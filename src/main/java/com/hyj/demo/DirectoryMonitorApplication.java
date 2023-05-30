package com.hyj.demo;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableScheduling
@EnableTransactionManagement
@EnableAsync
@EnableKnife4j
public class DirectoryMonitorApplication {

    public static void main(String[] args) {
        SpringApplication.run(DirectoryMonitorApplication.class, args);
    }
}

