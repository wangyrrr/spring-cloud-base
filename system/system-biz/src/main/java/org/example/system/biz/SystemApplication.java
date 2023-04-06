package org.example.system.biz;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;

/**
 * @Author: WangYuanrong
 * @Date: 2022/12/28 11:29
 */

@MapperScan("org.example.system.biz.mapper")
@ComponentScan("org.example")
@EnableDiscoveryClient
@SpringBootApplication
@Slf4j
public class SystemApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext application = SpringApplication.run(SystemApplication.class, args);
        Environment env = application.getEnvironment();
        String port = env.getProperty("server.port");
        log.info("service start success. env:{}, port:{}", env.getActiveProfiles(), port);
    }
}
