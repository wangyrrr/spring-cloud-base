package org.example.search.biz;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Author: WangYuanrong
 * @Date: 2022/12/28 11:29
 */
@EnableFeignClients(basePackages = {"org.example"})
@ComponentScan("org.example")
@EnableDiscoveryClient
@SpringBootApplication
@Slf4j
public class SearchApplication {

    public static void main(String[] args) {
        SpringApplication.run(SearchApplication.class, args);
    }
}
