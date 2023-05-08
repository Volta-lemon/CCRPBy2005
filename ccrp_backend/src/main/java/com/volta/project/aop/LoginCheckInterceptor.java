package com.volta.project.aop;

import com.alibaba.fastjson.JSONObject;
import com.volta.project.common.JwtUtils;
import com.volta.project.common.Result;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Component
public class LoginCheckInterceptor implements HandlerInterceptor {
    @Override // 目标方法执行前运行，返回TRUE放行，反之拦截
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        //获取请求URL
        String url = request.getRequestURL().toString();
        log.info("请求URL：{}",url);

        //判断URL中是否含有“login”
        if(url.contains("login")){
            log.info("放行...");
            return true;
        }
        if(url.contains("register")){
            log.info("放行...");
            return true;
        }

        //获取请求头中的token
        String jwt = request.getHeader("token");

        //解析令牌，如果不存在，返回错误结果(重新登陆)
        if(!StringUtils.hasLength(jwt)){
            log.info("请求头token为空,返回登录信息");
            Result error = Result.error("NOT_LOGIN");
            String notLogin = JSONObject.toJSONString(error);
            response.getWriter().write(notLogin);
            return false;
        }

        //解析
        try {
            Claims claims = JwtUtils.parseJwt(jwt);
//            System.out.println("********************************************************");
            System.out.println(claims);
//            System.out.println("********************************************************");
        } catch (Exception e) {
            e.printStackTrace();
            log.info("解析令牌失败，返回登录错误信息");
            Result error = Result.error("NOT_LOGIN");
            String notLogin = JSONObject.toJSONString(error);
            response.getWriter().write(notLogin);
            return false;
        }



        //放行
        log.info("令牌合法，放行");
        return true;
    }

    @Override // 目标资源方法执行后运行
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override // 试图渲染完毕后运行
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
