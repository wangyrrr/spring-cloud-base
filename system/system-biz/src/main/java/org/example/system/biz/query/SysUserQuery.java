package org.example.system.biz.query;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.example.common.param.PageParam;
import org.example.system.biz.entity.SysUser;

/**
 * @Author: wangyuanrong
 * @Date: 2023-04-08  10:40
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class SysUserQuery extends PageParam {

    private Long id;


    /**
     * 用户名
     */
    private String username;

    /**
     * 真实名
     */
    private String realName;

    /**
     * 性别，1：男，2：女
     */
    private Integer gender;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 状态，0：冻结，1：正常
     */
    private Integer status;

    public Wrapper<SysUser> buildWrapper() {
        return new LambdaQueryWrapper<SysUser>()
                .like(StringUtils.isNotBlank(username), SysUser::getUsername, username)
                .like(StringUtils.isNotBlank(realName), SysUser::getRealName, realName)
                .eq(gender != null, SysUser::getGender, gender)
                .like(StringUtils.isNotBlank(mobile), SysUser::getMobile, mobile)
                .eq(status != null, SysUser::getStatus, status);
    }
}
