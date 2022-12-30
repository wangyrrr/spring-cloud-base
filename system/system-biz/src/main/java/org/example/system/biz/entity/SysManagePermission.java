package org.example.system.biz.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 后台菜单权限表
 * @TableName sys_manage_permission
 */
@TableName(value ="sys_manage_permission")
@Data
public class SysManagePermission implements Serializable {
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
     * 父id
     */
    private Long parentId;

    /**
     * 类型，1：一级菜单，2：二级菜单，3：三级菜单，4：按钮
     */
    private Integer menuType;

    /**
     * 权限名称
     */
    private String name;

    /**
     * 权限编码，唯一
     */
    private String code;

    /**
     * 前端菜单路径或者按钮权限编码
     */
    private String permPath;

    /**
     * 图标
     */
    private String icon;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 备注
     */
    private String remark;

    /**
     * 软删除，0：正常，1：已删除
     */
    private Boolean deleted;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}