package org.example.gateway.enums;

import lombok.Getter;

/**
 * @Author: WangYuanrong
 * @Date: 2021/4/7 11:10
 */
@Getter
public enum ResultCodeEnum {

    SUCCESS(200,"请求成功"),

    FAIL(400,"请求无效"),

    UNAUTHORIZED(401,"鉴权失败"),

    ERROR(500,"系统繁忙")
    ;

    private final Integer code;

    private final String msg;

    ResultCodeEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
