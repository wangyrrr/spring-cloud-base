package org.example.common.config;

import org.example.common.aop.SystemLoginUserMethodArgumentResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * @Author: WangYuanrong
 * @Date: 2021/4/8 11:37
 */
@Configuration
public class LoginUserWebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(loginUserMethodArgumentResolver());
    }

    @Bean
    public HandlerMethodArgumentResolver loginUserMethodArgumentResolver(){
        return new SystemLoginUserMethodArgumentResolver();
    }


}
