package com.czu.personalized_mall;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.czu.personalized_mall.mapper")
public class PersonalizedMallApplication {

    public static void main(String[] args) {
        SpringApplication.run(PersonalizedMallApplication.class, args);
    }

}
