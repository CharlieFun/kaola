package com.netease.kaola.controller;

import com.netease.kaola.service.RoleBiz;
import com.netease.kaola.service.UserBiz;
import org.apache.ibatis.annotations.Param;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by funstar on 2018/2/9.
 */
@Controller
public class LoginAndRegisterController {
    public static final Logger LOGGER = LoggerFactory.getLogger(LoginAndRegisterController.class);
    @Autowired
    private UserBiz userBiz;
    @Autowired
    private RoleBiz roleBiz;

    @RequestMapping(value = "/login")
    public String doLogin(HttpServletRequest request) {
        String exceptionClassName = (String) request.getAttribute("shiroLoginFailure");
        String error = null;
        if (UnknownAccountException.class.getName().equals(exceptionClassName)) {
            error = "用户名";
        } else if (IncorrectCredentialsException.class.getName().equals(exceptionClassName)) {
            error = "密码错误";
        } else if (exceptionClassName != null) {
            error = "其他错误：" + exceptionClassName;
        }
        LOGGER.info("错误信息：{}", error);
        Subject subject = SecurityUtils.getSubject();
        boolean isAuthenticated = subject.isAuthenticated();
        LOGGER.info("用户{}是否验证成功：{}", subject.getPrincipal(), isAuthenticated);
        if (isAuthenticated) {
            if (subject.hasRole("seller")) {
                return "redirect:/seller";
            } else {
                return "/index";
            }
        }
        return "/login";
    }

    @RequestMapping(value = "register", method = RequestMethod.GET)
    public String registerView() {
        return "/register";
    }

    @RequestMapping(value = "register", method = RequestMethod.POST)
    public String doRegister(@Param("username") String username, @Param("password") String password,
                             @Param("role") String role) {
        boolean successFlag = userBiz.add(username, password, role);
        if (!successFlag) {
            return "redirect:/register";
        }
        return "redirect:/login";
    }
}
