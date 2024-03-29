package org.example.system.biz.controller;

import org.example.common.exception.ApiException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @Author: WangYuanrong
 * @Date: 2023/1/13 10:29
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    @GetMapping("/error")
    public void error() {
        throw new ApiException("错误");
    }


    @PostMapping("/http")
    public void http(HttpServletRequest request, @RequestBody Map<String, Object> map) {
        final String token = request.getHeader("token");
        System.out.println(token);
        System.out.println(map);
    }
}
