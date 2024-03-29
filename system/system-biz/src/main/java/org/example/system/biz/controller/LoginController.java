package org.example.system.biz.controller;

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: WangYuanrong
 * @Date: 2022/12/29 14:51
 */
@RestController
@RequestMapping("/auth")
@Slf4j
public class LoginController {


    @GetMapping("/login")
    public SaTokenInfo login() {
        StpUtil.login(1L);
        SaTokenInfo tokenInfo = StpUtil.getTokenInfo();

        return tokenInfo;
    }


    @GetMapping("/userInfo")
    public Object userInfo() {
        Object loginIdDefaultNull = StpUtil.getLoginIdDefaultNull();
        return loginIdDefaultNull;
    }


}
