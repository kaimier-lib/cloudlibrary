package com.ybvtc;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.ybvtc.mapper")
public class CloudlibraryApplication {

    public static void main(String[] args) {
        SpringApplication.run(CloudlibraryApplication.class, args);
    }

}
