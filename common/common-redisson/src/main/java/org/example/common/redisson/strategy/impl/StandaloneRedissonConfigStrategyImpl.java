package org.example.common.redisson.strategy.impl;

import lombok.extern.slf4j.Slf4j;
import org.example.common.core.constant.Constant;
import org.example.common.redisson.config.RedissonProperties;
import org.example.common.redisson.strategy.RedissonConfigStrategy;
import org.redisson.config.Config;
import org.springframework.util.StringUtils;


/**
 * 单机方式Redisson配置
 */
@Slf4j
public class StandaloneRedissonConfigStrategyImpl implements RedissonConfigStrategy {

    @Override
    public Config createRedissonConfig(RedissonProperties redissonProperties) {
        Config config = new Config();
        try {
            String address = redissonProperties.getAddress();
            String password = redissonProperties.getPassword();
            int database = redissonProperties.getDatabase();
            String redisAddr = Constant.REDIS_CONNECTION_PREFIX + address;
            config.useSingleServer().setAddress(redisAddr);
            config.useSingleServer().setDatabase(database);
            if (!StringUtils.isEmpty(password)) {
                config.useSingleServer().setPassword(password);
            }
            log.info("初始化Redisson单机配置,连接地址:" + address);
        } catch (Exception e) {
            log.error("单机Redisson初始化错误", e);
            e.printStackTrace();
        }
        return config;
    }
}
