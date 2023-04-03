package org.example.user.biz;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Author: WangYuanrong
 * @Date: 2022/12/28 11:29
 */

@MapperScan("org.example.user.biz.mapper")
@ComponentScan("org.example")
@EnableDiscoveryClient
@SpringBootApplication
@Slf4j
public class UserApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class, args);
    }
}
