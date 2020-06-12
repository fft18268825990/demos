package com.rabbitmq.interceptor;

import com.rabbitmq.service.UserService;
import com.rabbitmq.util.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.logging.Handler;

@Component
public class JwtInterceptor implements HandlerInterceptor {

    @Autowired
    UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("==========进入拦截器==========");
        String token = request.getHeader("token");
        // 如果不是映射到方法直接通过
//        if(handler instanceof HandlerMethod){
//            System.out.println("==========不是映射到方法直接通过==========");
//            return true;
//        }
        if(token != null){
            String username = JWTUtil.getUserNameByToken(request);
            boolean result = JWTUtil.verify(token,username,userService.getPassword(username));
            if(result){
                System.out.println("==========通过拦截器==========");
                return true;
            }
        }
        System.out.println("==========拦截器拦截==========");
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }

}
