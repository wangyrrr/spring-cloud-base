package org.example.system.biz.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.example.common.response.PageResult;
import org.example.system.api.vo.SysUserVO;
import org.example.system.biz.entity.SysUser;
import org.example.system.biz.query.SysUserQuery;

/**
 *
 */
public interface SysUserService extends IService<SysUser> {


    /**
     * 根据id查询
     * @param id
     * @return
     */
    SysUserVO findById(Long id);


    /**
     * 创建
     * @param user
     */
    void create(SysUser user);

    /**
     * 编辑
     * @param user
     */
    void update(SysUser user);

    /**
     * 查询
     * @param query
     * @return
     */
    PageResult<SysUser> findPage(SysUserQuery query);

}
