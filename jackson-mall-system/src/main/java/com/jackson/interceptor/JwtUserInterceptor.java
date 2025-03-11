package com.jackson.interceptor;

import com.jackson.constant.MemberConstant;
import com.jackson.context.BaseContext;
import com.jackson.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
public class JwtUserInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //判断当前拦截到的是Controller的方法还是其他资源
        if (!(handler instanceof HandlerMethod)) {
            //当前拦截到的不是动态方法，直接放行
            return true;
        }
        String token = request.getHeader("Authorization");
        //2.校验令牌
        try {
            log.info("jwt校验:{}", token);
            Claims claims = JwtUtils.parseJwt(token);
            Long userId = Long.valueOf(claims.get(MemberConstant.member_id).toString());
            log.info("登录员工的id:{}", userId);
            //使用ThreadLocal对新增员工操作者的id的储存
            BaseContext.setCurrentId(userId);
            return true;
        } catch (Exception e) {
            log.info("解析令牌失败");
            response.setStatus(401);
            return false;
        }
    }
}
