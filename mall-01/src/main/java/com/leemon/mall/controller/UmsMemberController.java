package com.leemon.mall.controller;

import com.leemon.mall.common.api.CommenResult;
import com.leemon.mall.service.UmsMemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author limenglong
 * @create 2019-08-08 16:29
 * @desc 会员登录注册管理Controller
 **/

@Api(tags = "UmsMemberController", description = "会员登录注册管理")
@RestController
@RequestMapping("/sso")
public class UmsMemberController {

    @Autowired
    private UmsMemberService umsMemberService;

    @ApiOperation("获取验证码")
    @GetMapping("/getAuthCode")
    public CommenResult getAuthCode(@RequestParam String tel) {
        return umsMemberService.generateAuthCode(tel);
    }

    @ApiOperation("校验验证码")
    @PostMapping("/verifyAuthCode")
    public CommenResult verifyAuthCode(@RequestParam String tel, @RequestParam String authCode) {
        return umsMemberService.verifyAuthCode(tel, authCode);
    }

}
