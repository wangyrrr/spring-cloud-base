package org.example.search.biz.mq;

import com.alibaba.fastjson2.JSON;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.example.common.constant.Constant;
import org.example.search.api.dto.ConsumerDTO;
import org.example.search.biz.model.Consumer;
import org.example.search.biz.repository.ConsumerRepository;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@RabbitListener(queues = Constant.CONSUMER_QUEUE)
@Component
public class ConsumerQueueHandler {

    @Autowired
    private ConsumerRepository consumerRepository;

    @RabbitHandler
    public void handlerManualAck(ConsumerDTO dto, Message message, Channel channel) {
        //  如果手动ACK,消息会被监听消费,但是消息在队列中依旧存在,如果 未配置 acknowledge-mode 默认是会在消费完毕后自动ACK掉
        try {
            log.info("处理队列，接收消息：{}", JSON.toJSONString(dto));
            Consumer consumer = new Consumer();
            BeanUtils.copyProperties(dto, consumer);
//            consumerRepository.save(consumer);
            // 通知 MQ 消息已被成功消费,可以ACK了
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (Exception e) {
            log.error("队列处理异常, 重新压入队列:{}" + JSON.toJSONString(dto), e);
            try {
                // 处理失败,重新压入MQ
                channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
            } catch (IOException e1) {
                log.error(e1.getMessage(), e1);
            }
        }
    }
}
