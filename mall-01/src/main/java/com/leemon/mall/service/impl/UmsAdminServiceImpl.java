package com.leemon.mall.service.impl;

import com.leemon.mall.common.utils.JwtTokenUtil;
import com.leemon.mall.dao.UmsAdminRoleRelationDao;
import com.leemon.mall.mbg.mapper.UmsAdminMapper;
import com.leemon.mall.mbg.model.UmsAdmin;
import com.leemon.mall.mbg.model.UmsAdminExample;
import com.leemon.mall.mbg.model.UmsPermission;
import com.leemon.mall.service.UmsAdminService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author limenglong
 * @create 2019-08-16 17:22
 * @desc UmsAdminService实现类
 **/
@Service
public class UmsAdminServiceImpl implements UmsAdminService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UmsAdminServiceImpl.class);

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UmsAdminMapper umsAdminMapper;

    @Autowired
    private UmsAdminRoleRelationDao umsAdminRoleRelationDao;

    @Override
    public UmsAdmin getAdminByUserName(String username) {
        UmsAdminExample umsAdminExample = new UmsAdminExample();
        umsAdminExample.createCriteria().andUsernameEqualTo(username);
        List<UmsAdmin> umsAdmins = umsAdminMapper.selectByExample(umsAdminExample);
        if (umsAdmins != null && umsAdmins.size() > 0) {
            return umsAdmins.get(0);
        }
        return null;
    }

    @Override
    public UmsAdmin register(UmsAdmin umsAdmin) {
        UmsAdmin admin = new UmsAdmin();
        BeanUtils.copyProperties(umsAdmin, admin);

        admin.setCreateTime(new Date());
        admin.setStatus(1);
        //查询是否有相同的用户
        UmsAdminExample example = new UmsAdminExample();
        example.createCriteria().andUsernameEqualTo(admin.getUsername());
        List<UmsAdmin> umsAdmins = umsAdminMapper.selectByExample(example);
        if (umsAdmins.size() > 0) {
            return null;
        }

        //将密码加密
        String encodePassword = passwordEncoder.encode(admin.getPassword());
        admin.setPassword(encodePassword);
        umsAdminMapper.insert(admin);
        return admin;
    }

    @Override
    public String login(String username, String password) {
        String token = null;

        try {
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            if (!passwordEncoder.matches(password, userDetails.getPassword())) {
                throw new BadCredentialsException("密码不正确");
            }
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            token = jwtTokenUtil.generateToken(userDetails);
        }catch (AuthenticationException e){
            LOGGER.warn("登录异常:{}",e.getMessage());
        }
        return token;
    }

    @Override
    public List<UmsPermission> getPermissionList(Long adminId) {
        return umsAdminRoleRelationDao.getPermissionList(adminId);
    }
}
