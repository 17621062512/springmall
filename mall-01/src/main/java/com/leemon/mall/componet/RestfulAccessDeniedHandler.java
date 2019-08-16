package com.leemon.mall.componet;

import cn.hutool.json.JSONUtil;
import com.leemon.mall.common.api.CommenResult;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author limenglong
 * @create 2019-08-16 16:15
 * @desc 没有权限时的处理
 **/
@Component
public class RestfulAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.getWriter().println(JSONUtil.parse(CommenResult.forbidden(accessDeniedException.getMessage())));
        response.getWriter().flush();
    }
}
