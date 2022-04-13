package org.example.common.redisson.config;


import lombok.extern.slf4j.Slf4j;
import org.example.common.redisson.enums.RedisConnectionType;
import org.example.common.redisson.strategy.RedissonConfigStrategy;
import org.example.common.redisson.strategy.impl.ClusterRedissonConfigStrategyImpl;
import org.example.common.redisson.strategy.impl.MasterslaveRedissonConfigStrategyImpl;
import org.example.common.redisson.strategy.impl.SentinelRedissonConfigStrategyImpl;
import org.example.common.redisson.strategy.impl.StandaloneRedissonConfigStrategyImpl;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.util.Assert;


/**
 * Redisson配置管理器，用于初始化的redisson实例
 */
@Slf4j
public class RedissonManager {

    private Config config = new Config();

    private RedissonClient redisson = null;

    public RedissonManager() {
    }

    public RedissonManager(RedissonProperties redissonProperties) {
        //装配开关
        Boolean enabled = redissonProperties.getEnabled();
        if (enabled) {
            try {
                config = RedissonConfigFactory.getInstance().createConfig(redissonProperties);
                redisson = Redisson.create(config);
            } catch (Exception e) {
                log.error("Redisson初始化错误", e);
            }
        }
    }

    public RedissonClient getRedisson() {
        return redisson;
    }

    /**
     * Redisson连接方式配置工厂
     * 双重检查锁
     */
    static class RedissonConfigFactory {

        private RedissonConfigFactory() {
        }

        private static volatile RedissonConfigFactory factory = null;

        public static RedissonConfigFactory getInstance() {
            if (factory == null) {
                synchronized (Object.class) {
                    if (factory == null) {
                        factory = new RedissonConfigFactory();
                    }
                }
            }
            return factory;
        }

        /**
         * 根据连接类型創建连接方式的配置
         *
         * @param redissonProperties
         * @return Config
         */
        Config createConfig(RedissonProperties redissonProperties) {
            Assert.notNull(redissonProperties, "redisson配置为空");
            Assert.notNull(redissonProperties.getAddress(), "redis地址未配置");
            RedisConnectionType connectionType = redissonProperties.getType();
            // 声明连接方式
            RedissonConfigStrategy redissonConfigStrategy;
            if (connectionType.equals(RedisConnectionType.SENTINEL)) {
                redissonConfigStrategy = new SentinelRedissonConfigStrategyImpl();
            } else if (connectionType.equals(RedisConnectionType.CLUSTER)) {
                redissonConfigStrategy = new ClusterRedissonConfigStrategyImpl();
            } else if (connectionType.equals(RedisConnectionType.MASTERSLAVE)) {
                redissonConfigStrategy = new MasterslaveRedissonConfigStrategyImpl();
            } else {
                redissonConfigStrategy = new StandaloneRedissonConfigStrategyImpl();
            }
            Assert.notNull(redissonConfigStrategy, "连接方式创建异常");

            return redissonConfigStrategy.createRedissonConfig(redissonProperties);
        }
    }


}
