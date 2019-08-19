package com.leemon.mall.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author limenglong
 * @create 2019-08-02 17:45
 * @desc MyBatis配置类
 **/
@Configuration
@MapperScan({"com.leemon.mall.mbg.mapper", "com.leemon.mall.dao"})
public class MyBatisConfig {
}
