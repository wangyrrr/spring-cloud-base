package org.example.common.constant;

/**
 * 常量定义
 * @Author: WangYuanrong
 * @Date: 2021/6/22 9:05
 */
public final class Constant {


    /**
     * 重复提交redis key
     */
    public static final String RESUBMIT_LOCK_KEY = "resubmit:key:";

    /**
     * redis 连接前缀
     */
    public static final String REDIS_CONNECTION_PREFIX = "redis://";


    public static final String CONSUMER_QUEUE = "testBaseQueue";

    public static final String DEMO_QUEUE = "demoBaseQueue";
}
