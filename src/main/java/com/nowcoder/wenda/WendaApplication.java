package com.nowcoder.wenda;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

//这是主要访问界面，启动界面
@SpringBootApplication
public class WendaApplication {
    public static void main(String[] args) {
        SpringApplication.run(WendaApplication.class, args);
    }

}
