package org.example.system.biz.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.example.system.api.vo.SysUserVO;
import org.example.system.biz.entity.SysUser;

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
}
