package com.example.intelligentscheduling;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Slf4j
@SpringBootApplication
@ServletComponentScan
@EnableTransactionManagement //开启事务注解的支持
public class IntelligentSchedulingApplication {
//com.example.intelligentscheduling.mapper.SchedulingMapper
    public static void main(String[] args) {
        SpringApplication.run(IntelligentSchedulingApplication.class, args);
    }

}
