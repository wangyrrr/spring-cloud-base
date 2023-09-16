package org.example.system.biz.controller;

import org.example.common.response.PageResult;
import org.example.common.valid.Insert;
import org.example.common.valid.Update;
import org.example.system.api.vo.SysUserVO;
import org.example.system.biz.entity.SysUser;
import org.example.system.biz.query.SysUserQuery;
import org.example.system.biz.service.SysUserService;
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

/**
 * 用户接口
 * @Author: WangYuanrong
 * @Date: 2022/12/29 16:33
 */
@RestController
@RequestMapping("/sysUser")
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;

    /**
     * 根据id查询
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public SysUserVO findById(@PathVariable Long id) {
        return sysUserService.findById(id);
    }

    /**
     * 分页查询
     * @param query
     * @return
     */
    @GetMapping
    public PageResult<SysUser> findPage(SysUserQuery query) {
        return sysUserService.findPage(query);
    }

    /**
     * 创建用户
     * @param user
     */
    @PostMapping
    public void create(@RequestBody @Validated({Insert.class, Default.class}) SysUser user) {
        sysUserService.create(user);
    }

    /**
     * 更新用户
     * @param user
     */
    @PutMapping
    public void update(@RequestBody @Validated({Update.class, Default.class}) SysUser user) {
        sysUserService.update(user);
    }

    /**
     * 删除用户
     * @param id
     */
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        sysUserService.removeById(id);
    }
}
