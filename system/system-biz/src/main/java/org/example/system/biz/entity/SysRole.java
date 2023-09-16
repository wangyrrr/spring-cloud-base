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
 * 系统角色表
 * @TableName sys_role
 */
@TableName(value ="sys_role")
@Data
public class SysRole implements Serializable {
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
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新人
     */
    private Long updateBy;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 角色名称
     */
    @NotBlank
    private String roleName;

    /**
     * 角色编码
     */
    @NotBlank
    private String roleCode;

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
     * 权限
     */
    @TableField(exist = false)
    private List<Long> permissions;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}