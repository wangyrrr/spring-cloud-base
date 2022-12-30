package org.example.search.biz.config;

import org.example.common.constant.Constant;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: WangYuanrong
 * @Date: 2022/12/28 11:38
 */
@Configuration
public class RabbitQueueConfig {

    /**
     * 队列
     */
    @Bean
    public Queue consumerQueue() {
        return new Queue(Constant.CONSUMER_QUEUE, true);
    }

}
