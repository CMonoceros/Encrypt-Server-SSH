package com.dhu.cst.zjm.util;

import com.alibaba.fastjson.JSON;
import com.dhu.cst.zjm.entity.base.SessionBaseEntity;
import com.dhu.cst.zjm.entity.base.UserBaseEntity;
import com.dhu.cst.zjm.service.BaseSessionService;
import com.dhu.cst.zjm.service.BaseUserService;
import com.dhu.cst.zjm.service.impl.SessionServiceImpl;
import com.dhu.cst.zjm.service.impl.UserServiceImpl;
import org.apache.commons.io.IOUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.annotation.Resource;
import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;

/**
 * Created by zjm on 2017/3/17.
 */
public class CookieFilter implements Filter {

    String userId = "";
    String sessionId = "";


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        System.out.println("Filter Url:"+req.getRequestURI());
        HttpServletResponse resp = (HttpServletResponse) response;
        HttpSession hs = req.getSession();
        Cookie[] cookies = req.getCookies();
        if (req.getRequestURI().indexOf("/App/login.action") != -1) {
            System.out.println("Cookie App/login.action");
            chain.doFilter(request, response);
        }else if (req.getRequestURI().indexOf("/App/register.action") != -1) {
            System.out.println("Cookie App/register.action");
            chain.doFilter(request, response);
        }else if (cookies != null && cookies.length > 1) {
            UserBaseEntity user = (UserBaseEntity) hs.getAttribute("user_inf");
            if (user == null) {
                System.out.println("Cookie UserBaseEntity Null");
                for (int i = 0; i < cookies.length; i++) {
                    Cookie cookie = cookies[i];
                    if (cookie.getName().equalsIgnoreCase("userId")) {
                        userId = cookie.getValue();
                        System.out.println("Cookie userId "+userId);
                    }
                    if (cookie.getName().equalsIgnoreCase("sessionId")) {
                        sessionId = cookie.getValue();
                        System.out.println("Cookie sessionId "+sessionId);
                    }
                }
                if (!(userId.equals("") || sessionId.equals(""))) {
                    ServletContext context = req.getSession().getServletContext();
                    ApplicationContext ac = WebApplicationContextUtils
                            .getWebApplicationContext(context);
                    BaseSessionService sessionService = (BaseSessionService) ac.getBean("sessionService");
                    BaseUserService userService = (BaseUserService) ac.getBean("userService");
                    int userIdInt = Integer.parseInt(userId);
                    SessionBaseEntity sessionBaseEntity = sessionService.getSession(userIdInt);
                    if (sessionBaseEntity != null
                            && sessionBaseEntity.getUserId() == userIdInt
                            && sessionBaseEntity.getId().equals(sessionId)
                            && new Date().getTime() / 1000 < sessionBaseEntity.getTime()) {
                        hs.setAttribute("user_inf", userService.findUserByID(userIdInt));
                        System.out.println("Cookie UserBaseEntity setAttribute");
                        chain.doFilter(request, response);
                    } else {
                        System.out.println("Cookie Session Error");
                        for (Cookie cookie : cookies) {
                            if ("userId".equals(cookie.getName())) {
                                cookie = new Cookie("userId", "");
                                cookie.setPath("/");
                                cookie.setMaxAge(0);
                                resp.addCookie(cookie);
                            }
                            if ("sessionId".equals(cookie.getName())) {
                                cookie = new Cookie("sessionId", "");
                                cookie.setPath("/");
                                cookie.setMaxAge(0);
                                resp.addCookie(cookie);
                            }
                        }
                    }
                }
            } else {
                System.out.println("Cookie UserBaseEntity Exist");
                chain.doFilter(request, response);
            }
        }
    }

    @Override
    public void destroy() {

    }
}
