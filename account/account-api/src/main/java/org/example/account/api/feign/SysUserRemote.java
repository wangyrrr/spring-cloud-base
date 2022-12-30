package org.example.account.api.feign;

import org.example.account.api.api.SysUserApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @Author: WangYuanrong
 * @Date: 2022/12/29 16:25
 */
@FeignClient(contextId = "sysUserRemote", value = "account")
public interface SysUserRemote extends SysUserApi {

}
