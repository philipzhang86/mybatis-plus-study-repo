package com.jmalltech;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.jmalltech.mapper")
@SpringBootApplication
public class MainApplication2 {
    public static void main(String[] args) {
        SpringApplication.run(MainApplication2.class, args);
    }
}