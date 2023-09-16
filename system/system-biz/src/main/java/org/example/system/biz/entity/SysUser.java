package org.example.system.biz.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.example.common.valid.Insert;
import org.example.common.valid.Update;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 系统用户表
 * @TableName sys_user
 */
@TableName(value ="sys_user")
@Data
public class SysUser implements Serializable {
    /**
     * 
     */
    @Null(groups = Insert.class)
    @NotNull(groups = Update.class)
    @TableId(type = IdType.AUTO)
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
    @NotBlank
    private String username;

    /**
     * 真实名
     */
    private String realName;

    /**
     * 性别，1：男，2：女
     */
    private Integer gender;

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
    private Integer status;

    /**
     * 备注
     */
    private String remark;

    /**
     * 软删除，0：正常，1：已删除
     */
    @TableLogic
    private Boolean deleted;

    /**
     * 拥有角色
     */
    @TableField(exist = false)
    private List<Long> roles;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}