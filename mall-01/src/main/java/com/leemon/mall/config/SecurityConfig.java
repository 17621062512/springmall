package com.leemon.mall.config;

import com.leemon.mall.componet.JwtAuthenticationTokenFilter;
import com.leemon.mall.componet.RestAuthenticationEntryPoint;
import com.leemon.mall.componet.RestfulAccessDeniedHandler;
import com.leemon.mall.dto.AdminUserDetails;
import com.leemon.mall.mbg.model.UmsAdmin;
import com.leemon.mall.mbg.model.UmsPermission;
import com.leemon.mall.service.UmsAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.List;

/**
 * @author limenglong
 * @create 2019-08-13 10:44
 * @desc SpringSecurity配置
 **/

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UmsAdminService umsAdminService;

    @Autowired
    private RestfulAccessDeniedHandler accessDeniedHandler;

    @Autowired
    private RestAuthenticationEntryPoint authenticationEntryPoint;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable() //使用jWT，不需要设置防止跨站请求伪造
                .sessionManagement() //基于token,不需要session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                //允许网站静态资源的无授权访问
                .antMatchers(HttpMethod.GET,
                        "/",
                        "/*.html",
                        "/favicon.ico",
                        "/**/*.html",
                        "/**/*.css",
                        "/**/*.js",
                        "/swagger-resources/**",
                        "/v2/api-docs/**"
                )
                .permitAll()
                //对登录注册要允许匿名访问
                .antMatchers("/admin/login", "/admin/register")
                .permitAll()
                //跨域请求之前会先进行一次options请求，判断实际请求是否安全
                .antMatchers(HttpMethod.OPTIONS)
                .permitAll()
                //测试时全部允许访问
//                .antMatchers("/**")
//                .permitAll()
                //除上面外全部需要鉴权认证
                .anyRequest().authenticated();

        //禁用缓存
        http.headers().cacheControl();

        //添加jwt Filter
        http.addFilterBefore(JwtAuthenticationTokenFilter(), UsernamePasswordAuthenticationFilter.class);

        //添加自定义未授权和未登录结果返回
        http.exceptionHandling()
                .accessDeniedHandler(accessDeniedHandler)
                .authenticationEntryPoint(authenticationEntryPoint);

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());
    }

    @Bean
    public JwtAuthenticationTokenFilter JwtAuthenticationTokenFilter() {
        return new JwtAuthenticationTokenFilter();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        //获取登录用户信息
        return username -> {
            UmsAdmin admin = umsAdminService.getAdminByUserName(username);
            if (admin != null) {
                List<UmsPermission> permissionList = umsAdminService.getPermissionList(admin.getId());
                return new AdminUserDetails(admin,permissionList);
            }
            throw new UsernameNotFoundException("用户名或密码错误");
        };
    }
}
