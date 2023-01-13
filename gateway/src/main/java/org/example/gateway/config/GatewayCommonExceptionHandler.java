package org.example.gateway.config;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.gateway.enums.ResultCodeEnum;
import org.example.gateway.exception.ApiException;
import org.example.gateway.vo.Result;
import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @author Administrator
 */
@Component
@Order(-1)
@RequiredArgsConstructor
@Slf4j
public class GatewayCommonExceptionHandler implements ErrorWebExceptionHandler {
    private final ObjectMapper objectMapper;

    @Override
    public Mono<Void> handle(ServerWebExchange serverWebExchange, Throwable throwable) {
        log.error("网关异常", throwable);
        ServerHttpResponse response = serverWebExchange.getResponse();
        if (response.isCommitted()) {
            return Mono.error(throwable);
        }
        // header set
        response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
        if (throwable instanceof ResponseStatusException) {
            response.setStatusCode(((ResponseStatusException) throwable).getStatus());
        }
        return response
                .writeWith(Mono.fromSupplier(() -> {
                    DataBufferFactory bufferFactory = response.bufferFactory();
                    try {
                        Object writeObj = null;
                        if (throwable instanceof ApiException) {
                            ApiException exception = (ApiException) throwable;
                            Result res = new Result();
                            res.setCode(exception.getCode());
                            res.setMsg(exception.getMessage());
                            writeObj = res;
                        } else {
                            writeObj = Result.exception(ResultCodeEnum.ERROR.getCode(), throwable.getMessage());
                        }
                        return bufferFactory.wrap(objectMapper.writeValueAsBytes(writeObj));
                    } catch (JsonProcessingException e) {
                        log.warn("Error writing response", throwable);
                        return bufferFactory.wrap(new byte[0]);
                    }
                }));
    }
}
