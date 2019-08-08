package com.leemon.mall.service.impl;

import com.leemon.mall.common.api.CommenResult;
import com.leemon.mall.service.RedisService;
import com.leemon.mall.service.UmsMemberService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Random;

/**
 * @author limenglong
 * @create 2019-08-08 16:32
 * @desc UmsMemberService实现类
 **/
@Service
public class UmsMemberServiceImpl implements UmsMemberService {
    @Autowired
    private RedisService redisService;
    @Value("${redis.key.prefix.authCode}")
    private String REDIS_KEY_PREFIX_AUTH_CODE;
    @Value("${redis.key.prefix.expire}")
    private Long AUTH_CODE_EXPIRE_SECONDS;

    @Override
    public CommenResult generateAuthCode(String tel) {
        StringBuilder builder = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            builder.append(random.nextInt(10));
        }
        //验证码绑定收手机号并存储到redis
        redisService.set(REDIS_KEY_PREFIX_AUTH_CODE + tel, builder.toString());
        redisService.expire(REDIS_KEY_PREFIX_AUTH_CODE + tel, AUTH_CODE_EXPIRE_SECONDS);
        return CommenResult.success(builder.toString(), "获取验证码成功");
    }

    @Override
    public CommenResult verifyAuthCode(String tel, String authCode) {
        if (StringUtils.isEmpty(authCode)) {
            return CommenResult.failed("请输入验证码");
        }

        String realAuthCode = redisService.get(REDIS_KEY_PREFIX_AUTH_CODE + tel);
        boolean result = authCode.equals(realAuthCode);
        if (result) {
            return CommenResult.success(null, "验证码校验成功");
        } else {
            return CommenResult.validateFailed("验证码不正确");
        }
    }
}
