package org.example.system.api.api;


import org.example.common.response.Result;
import org.example.system.api.vo.SysUserVO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 内部api定义
 * @Author: WangYuanrong
 * @Date: 2022/12/29 15:54
 */
@RequestMapping("/innerApi/sysUser")
public interface SysUserApi {

    /**
     * 根据id查询
     * @param id
     * @return
     */
    @GetMapping("/findById")
    Result<SysUserVO> findById(@RequestParam Long id);
}
