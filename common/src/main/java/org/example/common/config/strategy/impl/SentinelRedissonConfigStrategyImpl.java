package org.example.common.config.strategy.impl;

import lombok.extern.slf4j.Slf4j;
import org.example.common.config.RedissonProperties;
import org.example.common.config.strategy.RedissonConfigStrategy;
import org.example.common.constant.Constant;
import org.redisson.config.Config;
import org.springframework.util.StringUtils;


/**
 * 哨兵方式Redis连接配置
 * 比如sentinel.conf里配置为sentinel monitor my-sentinel-name 127.0.0.1 6379 2,那么这里就配置my-sentinel-name
 * 配置方式:my-sentinel-name,127.0.0.1:26379,127.0.0.1:26389,127.0.0.1:26399
 */
@Slf4j
public class SentinelRedissonConfigStrategyImpl implements RedissonConfigStrategy {

    @Override
    public Config createRedissonConfig(RedissonProperties redissonProperties) {
        Config config = new Config();
        try {
            String address = redissonProperties.getAddress();
            String password = redissonProperties.getPassword();
            int database = redissonProperties.getDatabase();
            String[] addrTokens = address.split(",");
            String sentinelAliasName = addrTokens[0];
            // 设置redis配置文件sentinel.conf配置的sentinel别名
            config.useSentinelServers().setMasterName(sentinelAliasName);
            config.useSentinelServers().setDatabase(database);
            if (!StringUtils.isEmpty(password)) {
                config.useSentinelServers().setPassword(password);
            }
            // 设置哨兵节点的服务IP和端口
            for (int i = 1; i < addrTokens.length; i++) {
                config.useSentinelServers().addSentinelAddress(Constant.REDIS_CONNECTION_PREFIX + addrTokens[i]);
            }
            log.info("初始化哨兵方式Config,redisAddress:" + address);
        } catch (Exception e) {
            log.error("哨兵Redisson初始化错误", e);
            e.printStackTrace();
        }
        return config;
    }
}
