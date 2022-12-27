package org.example.common.bo;

import lombok.Data;

import java.io.Serializable;

/**
 * 系统登录用户对象
 * @Author: WangYuanrong
 * @Date: 2022/4/8 14:54
 */
@Data
public class SystemLoginUserBO implements Serializable {

    /**
     * 系统用户id
     */
    private Long sysUserId;
}
