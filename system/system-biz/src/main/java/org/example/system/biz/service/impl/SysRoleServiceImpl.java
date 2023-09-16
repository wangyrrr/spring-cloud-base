package org.example.system.biz.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.common.exception.ApiException;
import org.example.common.response.PageResult;
import org.example.system.biz.entity.SysRole;
import org.example.system.biz.entity.SysRoleManagePermission;
import org.example.system.biz.query.SysRoleQuery;
import org.example.system.biz.service.SysRoleManagePermissionService;
import org.example.system.biz.service.SysRoleService;
import org.example.system.biz.mapper.SysRoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 *
 */
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole>
implements SysRoleService{

    @Autowired
    private SysRoleManagePermissionService sysRoleManagePermissionService;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void create(SysRole role) {
        LambdaQueryWrapper<SysRole> wrapper = new LambdaQueryWrapper<SysRole>()
                .eq(SysRole::getRoleCode, role.getRoleCode());
        long count = this.count(wrapper);
        if (count > 0) {
            throw new ApiException("角色编码已存在");
        }
        this.save(role);
        if (CollectionUtils.isNotEmpty(role.getPermissions())) {
            List<SysRoleManagePermission> roleManagePermissionList = role.getPermissions().stream().map(p -> {
                SysRoleManagePermission roleManagePermission = new SysRoleManagePermission();
                roleManagePermission.setRoleId(role.getId());
                roleManagePermission.setManagePermissionId(p);
                return roleManagePermission;
            }).collect(Collectors.toList());
            sysRoleManagePermissionService.saveBatch(roleManagePermissionList);
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void update(SysRole role) {
        LambdaQueryWrapper<SysRole> wrapper = new LambdaQueryWrapper<SysRole>()
                .ne(SysRole::getId, role.getId())
                .eq(SysRole::getRoleCode, role.getRoleCode());
        long count = this.count(wrapper);
        if (count > 0) {
            throw new ApiException("角色编码已存在");
        }
        this.updateById(role);
        if (CollectionUtils.isNotEmpty(role.getPermissions())) {
            LambdaQueryWrapper<SysRoleManagePermission> rolePermissionWrapper = new LambdaQueryWrapper<SysRoleManagePermission>()
                    .eq(SysRoleManagePermission::getRoleId, role.getId());
            sysRoleManagePermissionService.remove(rolePermissionWrapper);
            List<SysRoleManagePermission> roleManagePermissionList = role.getPermissions().stream().map(p -> {
                SysRoleManagePermission roleManagePermission = new SysRoleManagePermission();
                roleManagePermission.setRoleId(role.getId());
                roleManagePermission.setManagePermissionId(p);
                return roleManagePermission;
            }).collect(Collectors.toList());
            sysRoleManagePermissionService.saveBatch(roleManagePermissionList);
        }
    }

    @Override
    public PageResult<SysRole> findPage(SysRoleQuery query) {
        Page<SysRole> page = this.page(new Page<>(query.getCurrent(), query.getPageSize()), query.buildWrapper());
        if (CollectionUtils.isNotEmpty(page.getRecords())) {
            page.getRecords().forEach(r -> {
                LambdaQueryWrapper<SysRoleManagePermission> roleManagePermissionLambdaQueryWrapper = new LambdaQueryWrapper<SysRoleManagePermission>()
                        .select(SysRoleManagePermission::getManagePermissionId)
                        .eq(SysRoleManagePermission::getRoleId, r.getId());
                List<SysRoleManagePermission> roleManagePermissionList = sysRoleManagePermissionService.list(roleManagePermissionLambdaQueryWrapper);
                List<Long> permissions = roleManagePermissionList.stream().map(SysRoleManagePermission::getManagePermissionId).collect(Collectors.toList());
                r.setPermissions(permissions);
            });
        }
        return BeanUtil.copyProperties(page, PageResult.class);
    }
}




