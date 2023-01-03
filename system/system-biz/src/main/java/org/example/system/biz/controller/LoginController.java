package org.example.system.biz.controller;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

/**
 * @Author: WangYuanrong
 * @Date: 2022/12/29 14:51
 */
@RestController
@RequestMapping("/auth")
@Slf4j
public class LoginController {

    @Value("${sys.jwt.key}")
    public String jwtKey;


//    private static final SecretKey KEY = Keys.hmacShaKeyFor("2162d3e65a421bc0ac76ae5acfe29c650becb73f2a9b8ce57941036331b1aaa8".getBytes(StandardCharsets.UTF_8));

    @GetMapping("/login")
    public String login() {
        SecretKey key = Keys.hmacShaKeyFor(jwtKey.getBytes(StandardCharsets.UTF_8));
        String token = Jwts.builder().setSubject("token").claim("type", "account").claim("userId", "1")
                .signWith(key).setIssuedAt(new Date()).compact();
        return token;
    }



    public static void main(String[] args) {

        SecretKey key = Keys.hmacShaKeyFor("2162d3e65a421bc0ac76ae5acfe29c650becb73f2a9b8ce57941036331b1aaa8".getBytes(StandardCharsets.UTF_8));
        String jws = Jwts.builder().setSubject("token").claim("type", "account").claim("userId", "1")
                .signWith(key).setIssuedAt(new Date()).compact();
        System.out.println(jws);
        final Claims body = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(jws).getBody();
        System.out.println(body);
    }
}
