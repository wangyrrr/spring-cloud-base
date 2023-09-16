package org.example.system.biz.controller;

import org.example.common.response.PageResult;
import org.example.common.valid.Insert;
import org.example.common.valid.Update;
import org.example.system.biz.entity.SysRole;
import org.example.system.biz.query.SysRoleQuery;
import org.example.system.biz.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.groups.Default;
import java.util.List;

/**
 * 角色接口
 * @Author: WangYuanrong
 * @Date: 2022/12/29 11:34
 */
@RestController
@RequestMapping("/sysRole")
public class SysRoleController {

    @Autowired
    private SysRoleService sysRoleService;

    /**
     * 根据id查询
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public SysRole findById(@PathVariable Long id) {
        return sysRoleService.getById(id);
    }

    /**
     * 分页查询
     * @param query
     * @return
     */
    @GetMapping
    public PageResult<SysRole> findPage(SysRoleQuery query) {
        return sysRoleService.findPage(query);
    }

    /**
     * 列表查询
     * @return
     */
    @GetMapping("/list")
    public List<SysRole> list() {
        return sysRoleService.list();
    }

    /**
     * 创建角色
     * @param role
     */
    @PostMapping
    public void create(@RequestBody @Validated({Insert.class, Default.class}) SysRole role) {
        sysRoleService.create(role);
    }

    /**
     * 更新角色
     * @param role
     */
    @PutMapping
    public void update(@RequestBody @Validated({Update.class, Default.class}) SysRole role) {
        sysRoleService.update(role);
    }

    /**
     * 删除角色
     * @param id
     */
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        sysRoleService.removeById(id);
    }
}
