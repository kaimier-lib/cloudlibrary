package com.ybvtc;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.ybvtc.mapper")
public class Demo07CloudlibraryApplication {

    public static void main(String[] args) {
        SpringApplication.run(Demo07CloudlibraryApplication.class, args);
    }

}
