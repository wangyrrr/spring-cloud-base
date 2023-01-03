package org.example.search.biz.repository;


import org.example.search.biz.model.Consumer;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

/**
 * @Author: WangYuanrong
 * @Date: 2022/12/28 11:13
 */
public interface ConsumerRepository extends ElasticsearchRepository<Consumer, Long> {

    Consumer findByOpenId(String openId);

    List<Consumer> findByOpenIdLike(String openId);

    List<Consumer> findByMobile(String mobile);

    void deleteByOpenId(String openId);
}
