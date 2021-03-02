package com.nowcoder.wenda.controller.interceptor;

import com.nowcoder.wenda.annotation.LoginRequired;
import com.nowcoder.wenda.util.HostHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

@Component
public class LoginRequiredInterceptor implements HandlerInterceptor {

    @Autowired
    private HostHolder hostHolder;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Method method = handlerMethod.getMethod();//获取拦截到的method对象
            LoginRequired loginRequired = method.getAnnotation(LoginRequired.class);//取此方法的注解

            //如果此method需要注解，却没有得到user对象，则重定向去login界面
            if (loginRequired != null&& hostHolder.getUser()==null){
                response.sendRedirect(request.getContextPath()+"/login");
                return false;
            }
        }

        return true;
    }
}
