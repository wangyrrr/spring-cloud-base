package org.example.account.biz.comtroller;

import org.example.account.api.vo.SysUserVO;
import org.example.account.biz.service.SysUserService;
import org.example.common.response.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public Result<SysUserVO> findById(@PathVariable Long id) {
        return Result.response(sysUserService.findById(id));
    }
}
