package org.example.account.biz.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.account.api.vo.SysUserVO;
import org.example.account.biz.entity.SysUser;
import org.example.account.biz.mapper.SysUserMapper;
import org.example.account.biz.service.SysUserService;
import org.springframework.stereotype.Service;

/**
 *
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser>
implements SysUserService{

    @Override
    public SysUserVO findById(Long id) {
        final SysUser sysUser = this.getById(id);
        return BeanUtil.copyProperties(sysUser, SysUserVO.class);
    }
}




