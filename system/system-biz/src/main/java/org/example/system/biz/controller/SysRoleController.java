package org.example.system.biz.controller;

import org.example.system.biz.entity.SysRole;
import org.example.system.biz.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: WangYuanrong
 * @Date: 2022/12/29 11:34
 */
@RestController
@RequestMapping("/sysRole")
public class SysRoleController {

    @Autowired
    private SysRoleService sysRoleService;

    @GetMapping("/{id}")
    public SysRole findById(@PathVariable Long id) {
        return sysRoleService.getById(id);
    }
}
