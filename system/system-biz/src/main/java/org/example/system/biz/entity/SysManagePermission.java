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
 * 后台菜单权限表
 * @TableName sys_manage_permission
 */
@TableName(value ="sys_manage_permission")
@Data
public class SysManagePermission implements Serializable {
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
     * 父id
     */
    private Long parentId;

    /**
     * 类型，0：按钮，1：一级菜单，2：二级菜单，3：三级菜单
     */
    @NotNull
    private Integer menuType;

    /**
     * 权限名称
     */
    @NotBlank
    private String name;

    /**
     * 权限编码，唯一
     */
    @NotBlank
    private String code;

    /**
     * 前端菜单路径
     */
    private String path;

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
     * 状态，0：关闭，1：开启
     */
    private Integer status;

    /**
     * 软删除，0：正常，1：已删除
     */
    @TableLogic
    private Boolean deleted;

    /**
     * 子菜单
     */
    @TableField(exist = false)
    private List<SysManagePermission> children;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}