package org.example.system.api.feign;

import org.example.system.api.api.SysUserApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @Author: WangYuanrong
 * @Date: 2022/12/29 16:25
 */
@FeignClient(contextId = "sysUserRemote", value = "system")
public interface SysUserRemote extends SysUserApi {

}
