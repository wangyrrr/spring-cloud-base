package org.example.gateway.filter;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSON;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.example.gateway.config.AuthProperties;
import org.example.gateway.enums.ResultCodeEnum;
import org.example.gateway.exception.ApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
@Slf4j
public class AuthorizationFilter implements GlobalFilter, Ordered {


    @Autowired
    private AuthProperties authProperties;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String currentUrl = exchange.getRequest().getURI().getPath();
        log.info("网关鉴权，currentUrl:{}", currentUrl);
        if (this.skip(currentUrl)) {
            return chain.filter(exchange);
        }

        String token = exchange.getRequest().getHeaders().getFirst("token");
        if (StringUtils.isEmpty(token)) {
            log.error("需要认证的url:{},token为空", currentUrl);
            throw new ApiException(ResultCodeEnum.UNAUTHORIZED);
        }
        SecretKey key = Keys.hmacShaKeyFor(authProperties.getJwtKey().getBytes(StandardCharsets.UTF_8));
        Claims body;
        try {
            body = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
        } catch (Exception e) {
            log.error("解析token异常", e);
            throw new ApiException(ResultCodeEnum.UNAUTHORIZED);
        }
        Date now = new Date();
        final long between = DateUtil.between(body.getIssuedAt(), now, DateUnit.SECOND);
        if (between > authProperties.getExpire()) {
            throw new ApiException(ResultCodeEnum.UNAUTHORIZED.getCode(), "token已失效");
        }
        final ServerHttpRequest request = exchange.getRequest().mutate().header("auth", JSON.toJSONString(body)).build();
        // TODO 临近过期刷新token, response header 返回
//        exchange.getResponse().getHeaders().add("token", "new token");
        final ServerWebExchange serverWebExchange = exchange.mutate().request(request).build();
        return chain.filter(serverWebExchange);
    }

    /**
     * 是否跳过鉴权
     * @param currentUrl
     * @return
     */
    private boolean skip(String currentUrl) {
        PathMatcher pathMatcher = new AntPathMatcher();
        if (CollectionUtil.isNotEmpty(authProperties.getSkipUrl())) {
            return authProperties.getSkipUrl().stream().anyMatch(skipPath -> pathMatcher.match(skipPath, currentUrl) || currentUrl.endsWith(skipPath));
        }
        return false;
    }


    @Override
    public int getOrder() {
        return 0;
    }


}
