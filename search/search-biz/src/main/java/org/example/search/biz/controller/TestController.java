package org.example.search.biz.controller;

import org.example.search.biz.model.Consumer;
import org.example.search.biz.repository.ConsumerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/query")
    public Boolean query(Consumer consumer) {
        consumer = new Consumer();
        consumer.setOpenId("xxx");
        consumer.setMobile("13111111111");
        consumerRepository.save(consumer);
        return true;
    }
}
