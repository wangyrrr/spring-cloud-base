package org.example.system.api.feign;

import org.example.system.api.api.SysUserApi;
import org.example.system.api.fallback.SysUserApiFallback;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * 系统用户远程调用客户端
 * 注意定义的path要跟api实现类的RequestMapping保持一致
 * @Author: WangYuanrong
 * @Date: 2022/12/29 16:25
 */
@FeignClient(contextId = "sysUserRemote", path = "/innerApi/sysUser", value = "system", fallbackFactory = SysUserApiFallback.class)
public interface SysUserRemote extends SysUserApi {

}
