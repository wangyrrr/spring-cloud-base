package org.example.common.param;

/**
 * 分页查询入参
 * @Author: WangYuanrong
 * @Date: 2022/1/27 14:34
 */
public class PageParam {

    /**
     * 第几页，最小值1
     */
    private int current = 1;

    /**
     * 每页几条，最大值500
     */
    private int size = 10;

}
