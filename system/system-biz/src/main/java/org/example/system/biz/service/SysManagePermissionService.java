package org.example.system.biz.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.example.system.biz.entity.SysManagePermission;

import java.util.List;

/**
 *
 */
public interface SysManagePermissionService extends IService<SysManagePermission> {

    /**
     * 创建菜单
     * @param permission
     */
    void create(SysManagePermission permission);


    /**
     * 更新菜单
     * @param permission
     */
    void update(SysManagePermission permission);

    /**
     * 变更状态
     * @param id
     * @param status
     */
    void updateStatus(Long id, Integer status);

    /**
     * 分页查询，返回树形结构
     * @return
     */
    List<SysManagePermission> findList();
}
