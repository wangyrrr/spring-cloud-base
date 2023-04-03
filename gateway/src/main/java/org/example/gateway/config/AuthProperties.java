package org.example.gateway.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 客户端鉴权配置
 * @Author: WangYuanrong
 * @Date: 2023/1/13 13:38
 */
@Data
@ConfigurationProperties(prefix = "sys.auth")
@Component
public class AuthProperties {

    /**
     * Jwt Key
     */
    public String jwtKey;

    /**
     * 跳过鉴权URL
     */
    public List<String> skipUrl;

    /**
     * token有效时间，秒
     */
    public Long expire;
}
