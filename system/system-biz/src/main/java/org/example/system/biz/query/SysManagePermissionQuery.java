package org.example.system.biz.query;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.example.common.param.PageParam;
import org.example.system.biz.entity.SysManagePermission;

/**
 * @Author: wangyuanrong
 * @Date: 2023-04-08  10:40
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class SysManagePermissionQuery extends PageParam {


    /**
     * 类型，0：按钮，1：一级菜单，2：二级菜单，3：三级菜单
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
     * 前端菜单路径
     */
    private String path;
    public Wrapper<SysManagePermission> buildWrapper() {
        return new LambdaQueryWrapper<SysManagePermission>()
                .eq(menuType != null, SysManagePermission::getMenuType, menuType)
                .like(StringUtils.isNotBlank(name), SysManagePermission::getName, name)
                .like(StringUtils.isNotBlank(code), SysManagePermission::getCode, code);
    }
}
