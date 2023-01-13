package org.example.search.biz.controller;

import org.example.common.constant.Constant;
import org.example.common.response.Result;
import org.example.search.api.dto.ConsumerDTO;
import org.example.search.biz.model.Consumer;
import org.example.search.biz.repository.ConsumerRepository;
import org.example.system.api.feign.SysUserRemote;
import org.example.system.api.vo.SysUserVO;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: WangYuanrong
 * @Date: 2022/12/28 11:15
 */
@RestController
public class TestController {

    @Autowired
    private ConsumerRepository consumerRepository;

    @Autowired
    private ElasticsearchRestTemplate elasticsearchRestTemplate;

    @Autowired
    private SysUserRemote sysUserRemote;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @GetMapping("/mq")
    public Boolean mq() {
        ConsumerDTO dto = new ConsumerDTO();
        dto.setOpenId("xxxx");
        dto.setMobile("13111111111");
        rabbitTemplate.convertAndSend(Constant.CONSUMER_QUEUE, dto);
        String s = "this is a string";
        rabbitTemplate.convertAndSend(Constant.DEMO_QUEUE, s);
        return true;
    }


    @GetMapping("/findUser")
    public Result<SysUserVO> findUser() {
        return sysUserRemote.findById(1L);
    }

    @GetMapping("/openId")
    public Consumer openId(String openId) {
        final Consumer consumer = consumerRepository.findByOpenId(openId);
        return consumer;
    }

    @GetMapping("/likeOpenId")
    public List<Consumer> likeOpenId(String openId) {
        return consumerRepository.findByOpenIdLike(openId);
    }


    @GetMapping("/mobile")
    public List<Consumer> mobile(String mobile) {
        return consumerRepository.findByMobile(mobile);
    }

    @GetMapping("/query")
    public List<Consumer> query(String mobile) {
        return null;
    }

}
