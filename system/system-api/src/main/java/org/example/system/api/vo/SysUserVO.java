package org.example.system.api.vo;

import lombok.Data;

import java.util.Date;

/**
 * @Author: WangYuanrong
 * @Date: 2022/12/29 16:20
 */
@Data
public class SysUserVO {

    /**
     * userId
     */
    private Long id;

    /**
     * 创建人
     */
    private Long createBy;

    /**
     * 创建日期
     */
    private Date createTime;

    /**
     * 更新人
     */
    private Long updateBy;

    /**
     * 更新日期
     */
    private Date updateTime;

    /**
     * 用户名
     */
    private String username;

    /**
     * 真实名
     */
    private String realName;

    /**
     * 性别，1：男，2：女
     */
    private Byte gender;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 密码
     */
    private String password;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 状态，0：冻结，1：正常
     */
    private Byte status;

    /**
     * 备注
     */
    private String remark;

}
