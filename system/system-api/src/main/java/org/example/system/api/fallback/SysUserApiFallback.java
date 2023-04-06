package org.example.system.api.fallback;

import org.example.common.response.Result;
import org.example.system.api.feign.SysUserRemote;
import org.example.system.api.vo.SysUserVO;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @Author: wangyuanrong
 * @Date: 2023-04-03  11:03
 */
@Component
public class SysUserApiFallback implements FallbackFactory<SysUserRemote> {

    @Override
    public SysUserRemote create(Throwable cause) {
        return new SysUserRemote() {
            @Override
            public Result<SysUserVO> findById(Long id) {
                return Result.exception();
            }
        };
    }
}
