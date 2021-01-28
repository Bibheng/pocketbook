package com.matthew.pocketbook.web;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 后台项目启动类
 *
 * @author Matthew
 * @date 2021-01-28 16:35
 **/
@SpringBootApplication(scanBasePackages = {"com.matthew.pocketbook"})
@MapperScan(basePackages = "com.matthew.pocketbook.**.dao")
@EnableTransactionManagement
public class WebApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebApplication.class, args);
    }

}
