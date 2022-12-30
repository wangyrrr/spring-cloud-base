package org.example.account.biz.comtroller.remote;

import org.example.account.api.api.SysUserApi;
import org.example.account.api.vo.SysUserVO;
import org.example.account.biz.service.SysUserService;
import org.example.common.response.Result;
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
        if (true) {
            throw new RuntimeException("exception");
        }
        return Result.response(sysUserService.findById(id));
    }
}
