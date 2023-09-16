package org.example.system.biz.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.example.common.response.PageResult;
import org.example.system.biz.entity.SysRole;
import org.example.system.biz.query.SysRoleQuery;

/**
 *
 */
public interface SysRoleService extends IService<SysRole> {

    /**
     * 创建角色
     * @param role
     */
    void create(SysRole role);

    /**
     * 编辑角色
     * @param role
     */
    void update(SysRole role);

    /**
     * 查询角色
     * @param query
     * @return
     */
    PageResult<SysRole> findPage(SysRoleQuery query);

}
