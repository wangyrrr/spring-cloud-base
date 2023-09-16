package org.example.system.biz.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.common.response.PageResult;
import org.example.system.api.vo.SysUserVO;
import org.example.system.biz.constant.SystemConstant;
import org.example.system.biz.entity.SysUser;
import org.example.system.biz.entity.SysUserRole;
import org.example.system.biz.mapper.SysUserMapper;
import org.example.system.biz.query.SysUserQuery;
import org.example.system.biz.service.SysUserRoleService;
import org.example.system.biz.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 *
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser>
implements SysUserService {

    @Autowired
    private SysUserRoleService sysUserRoleService;

    @Override
    public SysUserVO findById(Long id) {
        final SysUser sysUser = this.getById(id);
        return BeanUtil.copyProperties(sysUser, SysUserVO.class);
    }

    @Override
    public void create(SysUser user) {
        if (user.getStatus() == null) {
            user.setStatus(1);
        }
        if (StringUtils.isBlank(user.getPassword())) {
            user.setPassword(SystemConstant.DEFAULT_PASSWORD);
        }
        user.setPassword(SecureUtil.md5(user.getPassword()));
        this.save(user);
        if (CollectionUtils.isNotEmpty(user.getRoles())) {
            List<SysUserRole> userRoleList = user.getRoles().stream().map(r -> {
                SysUserRole userRole = new SysUserRole();
                userRole.setUserId(user.getId());
                userRole.setRoleId(r);
                return userRole;
            }).collect(Collectors.toList());
            sysUserRoleService.saveBatch(userRoleList);
        }
    }

    @Override
    public void update(SysUser user) {
        this.updateById(user);
        if (CollectionUtils.isNotEmpty(user.getRoles())) {
            LambdaQueryWrapper<SysUserRole> userRoleLambdaQueryWrapper = new LambdaQueryWrapper<SysUserRole>()
                    .eq(SysUserRole::getUserId, user.getId());
            sysUserRoleService.remove(userRoleLambdaQueryWrapper);
            List<SysUserRole> userRoleList = user.getRoles().stream().map(r -> {
                SysUserRole userRole = new SysUserRole();
                userRole.setUserId(user.getId());
                userRole.setRoleId(r);
                return userRole;
            }).collect(Collectors.toList());
            sysUserRoleService.saveBatch(userRoleList);
        }
    }

    @Override
    public PageResult<SysUser> findPage(SysUserQuery query) {
        Page<SysUser> page = this.page(new Page<>(query.getCurrent(), query.getPageSize()), query.buildWrapper());
        if (CollectionUtils.isNotEmpty(page.getRecords())) {
            page.getRecords().forEach(u -> {
                u.setPassword(null);
                LambdaQueryWrapper<SysUserRole> userRoleLambdaQueryWrapper = new LambdaQueryWrapper<SysUserRole>()
                        .select(SysUserRole::getRoleId)
                        .eq(SysUserRole::getUserId, u.getId());
                List<SysUserRole> userRoleList = sysUserRoleService.list(userRoleLambdaQueryWrapper);
                List<Long> roles = userRoleList.stream().map(SysUserRole::getRoleId).collect(Collectors.toList());
                u.setRoles(roles);
            });
        }
        return BeanUtil.copyProperties(page, PageResult.class);
    }
}




