package org.example.search.api.dto;

import lombok.Data;

import java.util.Date;

@Data
public class ConsumerDTO {

    private Long id;

    /**
     * 注册类型  1.自动注册 2.微信信息获取
     */
    private Integer registerType;

    /**
     * 小程序openid
     */
    private String openId;

    /**
     * 小程序在同一主体下唯一标识
     */
    private String unionId;

    /**
     * 是否是白象会员 0.不是 1.是，当为1是，id为白象推过来的用户id
     */
    private Integer wetMember;

    /**
     * 白象用户id
     */
    private Long wetMemberId;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 头像URL
     */
    private String avatarUrl;

    /**
     * 0.未知 1.男 2.女
     */
    private Integer gender;

    /**
     * 用户所在国家
     */
    private String country;

    /**
     * 用户所在省份
     */
    private String province;

    /**
     * 用户所在城市
     */
    private String city;

    /**
     * 手机号码
     */
    private String mobile;

    /**
     * 状态 1.启用 0.禁用
     */
    private Integer status;

    /**
     * 是否封号  1.未封号 0.封号
     */
    private Integer close;

    /**
     * 类型 1.小程序 2.公众号
     */
    private Integer type;

    /**
     * 风险等级
     */
    private Integer riskLevel;

    /**
     * 扫码次数
     */
    private Integer scanCount;

    /**
     * 中奖次数
     */
    private Integer rewardCount;

    /**
     * 创建人
     */
    private Long createBy;

    /**
     * 创建日期
     */
    private Date createTime;

    /**
     * 更新人
     */
    private Long updateBy;

    /**
     * 更新日期
     */
    private Date updateTime;

}
