package org.example.user.biz.controller;

import cn.dev33.satoken.stp.SaTokenInfo;
import lombok.extern.slf4j.Slf4j;
import org.example.common.utils.StpUserUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: WangYuanrong
 * @Date: 2022/12/29 14:51
 */
@RestController
@RequestMapping("/client")
@Slf4j
public class LoginController {

    @GetMapping("/login")
    public SaTokenInfo login() {
        StpUserUtil.login(1L);
        SaTokenInfo tokenInfo = StpUserUtil.getTokenInfo();
        return tokenInfo;
    }



    @GetMapping("/userInfo")
    public Object userInfo() {
        Object loginIdDefaultNull = StpUserUtil.getLoginIdDefaultNull();
        return loginIdDefaultNull;
    }

}
