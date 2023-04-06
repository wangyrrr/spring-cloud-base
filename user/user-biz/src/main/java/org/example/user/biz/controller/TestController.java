package org.example.user.biz.controller;

import org.example.common.exception.ApiException;
import org.example.common.response.Result;
import org.example.system.api.api.SysUserApi;
import org.example.system.api.vo.SysUserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: WangYuanrong
 * @Date: 2023/1/13 10:29
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private SysUserApi sysUserApi;

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    @GetMapping("/error")
    public void error() {
        throw new ApiException("错误");
    }


    @GetMapping("/http")
    public void http(HttpServletRequest request) {
        final String token = request.getHeader("token");
        final String auth = request.getHeader("auth");
        System.out.println(token);
        System.out.println(auth);
    }

    @GetMapping("/sysUser")
    public SysUserVO sysUser(@RequestParam Long id) {
        Result<SysUserVO> apiResult = sysUserApi.findById(id);
        if (apiResult.isOkAndHasData()) {
            return apiResult.getData();
        }
        return null;
    }
}
