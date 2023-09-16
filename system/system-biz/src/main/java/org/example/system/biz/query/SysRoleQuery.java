package org.example.system.biz.query;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.example.common.param.PageParam;
import org.example.system.biz.entity.SysRole;

import javax.validation.constraints.NotBlank;

/**
 * @Author: wangyuanrong
 * @Date: 2023-04-08  10:40
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class SysRoleQuery extends PageParam {

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

    public Wrapper<SysRole> buildWrapper() {
        return new LambdaQueryWrapper<SysRole>()
                .like(StringUtils.isNotBlank(roleName), SysRole::getRoleName, roleName)
                .like(StringUtils.isNotBlank(roleCode), SysRole::getRoleCode, roleCode);
    }
}
