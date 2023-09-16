package org.example.system.biz.controller;

import org.example.common.valid.Insert;
import org.example.common.valid.Update;
import org.example.system.biz.entity.SysManagePermission;
import org.example.system.biz.service.SysManagePermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.groups.Default;
import java.util.List;

/**
 * 管理权限（菜单）接口
 * @Author: wangyuanrong
 * @Date: 2023-04-15  16:54
 */
@RestController
@RequestMapping("/permission")
public class SysManagePermissionController {
    
    @Autowired
    private SysManagePermissionService sysManagePermissionService;

    /**
     * 根据id查询
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public SysManagePermission findById(@PathVariable Long id) {
        return sysManagePermissionService.getById(id);
    }

    /**
     * 查询菜单，返回树形结构
     * @return
     */
    @GetMapping
    public List<SysManagePermission> findList() {
        return sysManagePermissionService.findList();
    }


    /**
     * 创建菜单
     * @param permission
     */
    @PostMapping
    public void create(@RequestBody @Validated({Insert.class, Default.class}) SysManagePermission permission) {
        sysManagePermissionService.create(permission);
    }

    /**
     * 更新菜单
     * @param permission
     */
    @PutMapping
    public void update(@RequestBody @Validated({Update.class, Default.class}) SysManagePermission permission) {
        sysManagePermissionService.update(permission);
    }

    @PatchMapping
    public void updateStatus(Long id, Integer status) {
        sysManagePermissionService.updateStatus(id, status);
    }

    /**
     * 删除菜单
     * @param id
     */
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        sysManagePermissionService.removeById(id);
    }
}
