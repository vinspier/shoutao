package com.vinspier.filter;


import com.vinspier.pojo.User;
import com.vinspier.service.UserService;
import com.vinspier.serviceImpl.UserServiceImpl;
import com.vinspier.utils.CookieUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Administrator on 2017/11/30 0030.
 */
public class LoginFilter implements Filter {

    public void init(FilterConfig var1) throws ServletException{}

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException{
        HttpServletRequest httpServletRequest = (HttpServletRequest)request;
        HttpServletResponse httpServletResponse = (HttpServletResponse)response;

        String servletPath = httpServletRequest.getServletPath();
        if(servletPath.startsWith("/index") || servletPath.startsWith("/login")){
            chain.doFilter(httpServletRequest,httpServletResponse);
            return;
        }

        User loginUser = (User)httpServletRequest.getSession().getAttribute("user");
        //若已经登录 则放行让其通过
        if(loginUser != null){
            chain.doFilter(httpServletRequest,httpServletResponse);
            return;
        }
        //若没有自动登录cookie 就不需要自动登录
        Cookie userCookie = CookieUtils.getCookie(((HttpServletRequest) request).getCookies(),"autoLoginCookie");
        if(userCookie == null){
            chain.doFilter(httpServletRequest,httpServletResponse);
            return;
        }

        String[] loginMessage = userCookie.getValue().split("@");
        String username = loginMessage[0];
        String password = loginMessage[1];
        try {
            UserService userService = new UserServiceImpl();
            loginUser = userService.login(username,password);
            if(loginUser == null){
                chain.doFilter(httpServletRequest,httpServletResponse);
                return;
            }
            httpServletRequest.getSession().setAttribute("user",loginUser);
            chain.doFilter(httpServletRequest,httpServletResponse);
        } catch (Exception e) {
            System.out.println("自动登录异常，自动忽略");
        }
    }

    public void destroy(){}
}
