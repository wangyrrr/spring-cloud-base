package org.example.common.core.constant;

/**
 * 常量定义
 * @Author: WangYuanrong
 * @Date: 2021/6/22 9:05
 */
public final class Constant {

    /**
     * 登录用户redis session key
     */
    public static final String LOGIN_USER = "loginUser:";

    /**
     * 重复提交redis key
     */
    public static final String RESUBMIT_LOCK_KEY = "resubmit:key:";

    /**
     * redis 连接前缀
     */
    public static final String REDIS_CONNECTION_PREFIX = "redis://";
}
