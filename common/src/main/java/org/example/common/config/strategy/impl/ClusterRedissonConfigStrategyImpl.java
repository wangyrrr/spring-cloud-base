package org.example.common.config.strategy.impl;

import lombok.extern.slf4j.Slf4j;
import org.example.common.config.RedissonProperties;
import org.example.common.config.strategy.RedissonConfigStrategy;
import org.example.common.constant.Constant;
import org.redisson.config.Config;
import org.springframework.util.StringUtils;


/**
 * 集群方式Redisson配置
 * cluster方式至少6个节点(3主3从)
 * 配置方式:127.0.0.1:6379,127.0.0.1:6380,127.0.0.1:6381,127.0.0.1:6382,127.0.0.1:6383,127.0.0.1:6384
 */
@Slf4j
public class ClusterRedissonConfigStrategyImpl implements RedissonConfigStrategy {

    @Override
    public Config createRedissonConfig(RedissonProperties redissonProperties) {
        Config config = new Config();
        try {
            String address = redissonProperties.getAddress();
            String password = redissonProperties.getPassword();
            String[] addrTokens = address.split(",");
            // 设置集群(cluster)节点的服务IP和端口
            for (int i = 0; i < addrTokens.length; i++) {
                config.useClusterServers().addNodeAddress(Constant.REDIS_CONNECTION_PREFIX + addrTokens[i]);
                if (!StringUtils.isEmpty(password)) {
                    config.useClusterServers().setPassword(password);
                }
            }
            log.info("初始化集群方式Config,连接地址:" + address);
        } catch (Exception e) {
            log.error("集群Redisson初始化错误", e);
            e.printStackTrace();
        }
        return config;
    }
}
