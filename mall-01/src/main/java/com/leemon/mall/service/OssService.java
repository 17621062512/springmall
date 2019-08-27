package com.leemon.mall.service;

import com.leemon.mall.dto.OssCallbackResult;
import com.leemon.mall.dto.OssPolicyResult;

import javax.servlet.http.HttpServletRequest;

/**
 * oss上传管理service
 */
public interface OssService {
    //Oss上传策略生成
    OssPolicyResult policy();

    //Oss上传成功回调
    OssCallbackResult callback(HttpServletRequest request);
}
