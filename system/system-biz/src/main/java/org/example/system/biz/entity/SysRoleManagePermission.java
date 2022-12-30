package org.example.system.biz.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 角色后台权限表
 * @TableName sys_role_manage_permission
 */
@TableName(value ="sys_role_manage_permission")
@Data
public class SysRoleManagePermission implements Serializable {
    /**
     * 
     */
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
     * 角色id
     */
    private Long roleId;

    /**
     * 后台权限id
     */
    private Long managePermissionId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}