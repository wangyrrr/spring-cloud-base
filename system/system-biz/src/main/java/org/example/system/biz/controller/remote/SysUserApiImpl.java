package org.example.system.biz.controller.remote;

import org.example.common.response.Result;
import org.example.system.api.api.SysUserApi;
import org.example.system.api.vo.SysUserVO;
import org.example.system.biz.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户api实现
 * @Author: WangYuanrong
 * @Date: 2022/12/29 16:34
 */
@RestController
public class SysUserApiImpl implements SysUserApi {

    @Autowired
    private SysUserService sysUserService;

    @Override
    public Result<SysUserVO> findById(Long id) {
        return Result.response(sysUserService.findById(id));
    }
}
