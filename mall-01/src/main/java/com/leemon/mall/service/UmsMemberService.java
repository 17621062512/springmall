package com.leemon.mall.service;

import com.leemon.mall.common.api.CommenResult;

/**
 * 会员管理service
 */
public interface UmsMemberService {

    /**
     * 生成验证码
     */
    CommenResult generateAuthCode(String tel);

    /**
     * 判断验证码和手机号是否匹配
     */
    CommenResult verifyAuthCode(String tel, String authCode);
}
