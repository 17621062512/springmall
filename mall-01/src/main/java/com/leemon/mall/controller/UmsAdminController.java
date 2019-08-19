package com.leemon.mall.controller;

import com.leemon.mall.common.api.CommenResult;
import com.leemon.mall.dto.UmsAdminLoginParam;
import com.leemon.mall.mbg.model.UmsAdmin;
import com.leemon.mall.mbg.model.UmsPermission;
import com.leemon.mall.service.UmsAdminService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author limenglong
 * @create 2019-08-19 13:58
 * @desc 后台用户管理
 **/
@RestController
@Api(tags = "UmsAdminController", description = "后台用户管理")
@RequestMapping("/admin")
public class UmsAdminController {

    @Autowired
    private UmsAdminService umsAdminService;

    @Value("${jwt.tokenHeader}")
    private String tokenHeader;
    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @ApiOperation("用户注册")
    @PostMapping("/register")
    public CommenResult<UmsAdmin> register(@RequestBody UmsAdmin umsAdmin, BindingResult bindingResult) {
        UmsAdmin admin = umsAdminService.register(umsAdmin);
        if (admin == null) {
            CommenResult.failed();
        }

        return CommenResult.success(admin);
    }

    @ApiOperation("登陆以后返回token")
    @PostMapping("/login")
    public CommenResult login(@RequestBody UmsAdminLoginParam umsAdminLoginParam, BindingResult bindingResult) {
        String token = umsAdminService.login(umsAdminLoginParam.getUsername(), umsAdminLoginParam.getPassword());
        if (token == null) {
            return CommenResult.failed("用户名或密码错误");
        }

        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", token);
        tokenMap.put("tokenHead", tokenHead);
        return CommenResult.success(tokenMap);
    }

    @ApiOperation("获取用户所有权限（包括加减权限）")
    @GetMapping("/permission/{id}")
    @PreAuthorize("hasAuthority('pms:brand:update')")
    public CommenResult<List<UmsPermission>> getPermissionList(@PathVariable("id") Long id) {
        List<UmsPermission> permissionList = umsAdminService.getPermissionList(id);
        return CommenResult.success(permissionList);
    }
}
