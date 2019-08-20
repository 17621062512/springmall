package com.leemon.mall.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author limenglong
 * @create 2019-08-19 18:13
 * @desc Controller层的日志封装类
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WebLog {

    //操作描述
    private String description;
    //操作用户
    private String username;
    //操作时间
    private Long startTime;
    //消耗时长
    private Integer spendTime;
    //根路径
    private String basePath;
    //uri
    private String uri;
    //url
    private String url;
    //请求类型
    private String method;
    //ip地址
    private String ip;
    //请求参数
    private Object parameter;
    //请求返回的结果
    private Object result;
}
