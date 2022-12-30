package org.example.account.biz.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.example.account.api.vo.SysUserVO;
import org.example.account.biz.entity.SysUser;

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
