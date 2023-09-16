package org.example.system.biz.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.common.exception.ApiException;
import org.example.system.biz.entity.SysManagePermission;
import org.example.system.biz.mapper.SysManagePermissionMapper;
import org.example.system.biz.service.SysManagePermissionService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 *
 */
@Service
public class SysManagePermissionServiceImpl extends ServiceImpl<SysManagePermissionMapper, SysManagePermission>
implements SysManagePermissionService{

    @Override
    public void create(SysManagePermission permission) {
        LambdaQueryWrapper<SysManagePermission> wrapper = new LambdaQueryWrapper<SysManagePermission>()
                .eq(SysManagePermission::getCode, permission.getCode());
        long count = this.count(wrapper);
        if (count > 0) {
            throw new ApiException("菜单编码已存在");
        }
        if (permission.getMenuType() == 1) {
            permission.setParentId(0L);
        }
        this.save(permission);
    }

    @Override
    public void update(SysManagePermission permission) {
        LambdaQueryWrapper<SysManagePermission> wrapper = new LambdaQueryWrapper<SysManagePermission>()
                .ne(SysManagePermission::getId, permission.getId())
                .eq(SysManagePermission::getCode, permission.getCode());
        long count = this.count(wrapper);
        if (count > 0) {
            throw new ApiException("菜单编码已存在");
        }
        this.updateById(permission);
    }

    @Override
    public void updateStatus(Long id, Integer status) {
        LambdaUpdateWrapper<SysManagePermission> wrapper = new LambdaUpdateWrapper<SysManagePermission>()
                .set(SysManagePermission::getStatus, status)
                .eq(SysManagePermission::getId, id);
        this.update(wrapper);
    }

    @Override
    public List<SysManagePermission> findList() {
        List<SysManagePermission> list = this.list();
        List<SysManagePermission> collect = list.stream().filter(p -> p.getParentId() == 0).collect(Collectors.toList());
        collect.forEach(p -> this.buildChildren(p, list));
        return collect;
    }

    /**
     * 递归子菜单
     * @param permission
     * @param list
     */
    private void buildChildren(SysManagePermission permission, List<SysManagePermission> list) {
        List<SysManagePermission> children = list.stream().filter(p -> p.getParentId().equals(permission.getId())).collect(Collectors.toList());
        if (CollectionUtils.isNotEmpty(children)) {
            permission.setChildren(children);
            children.forEach(p -> this.buildChildren(p, list));
        }
    }
}




