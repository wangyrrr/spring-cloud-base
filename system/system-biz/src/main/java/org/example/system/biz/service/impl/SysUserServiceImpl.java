package org.example.system.biz.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.system.api.vo.SysUserVO;
import org.example.system.biz.entity.SysUser;
import org.example.system.biz.mapper.SysUserMapper;
import org.example.system.biz.service.SysUserService;
import org.springframework.stereotype.Service;

/**
 *
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser>
implements SysUserService {

    @Override
    public SysUserVO findById(Long id) {
        final SysUser sysUser = this.getById(id);
        return BeanUtil.copyProperties(sysUser, SysUserVO.class);
    }
}




