package org.example.common.config.strategy;

import org.example.common.config.RedissonProperties;
import org.redisson.config.Config;

/**
 * Redisson配置构建接口
 */
public interface RedissonConfigStrategy {

    /**
     * 根据不同的Redis配置策略创建对应的Config
     *
     * @param redissonProperties
     * @return Config
     */
    Config createRedissonConfig(RedissonProperties redissonProperties);
}
