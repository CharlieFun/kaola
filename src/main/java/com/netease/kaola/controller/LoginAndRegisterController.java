package com.netease.kaola.controller;

import com.netease.kaola.entity.User;
import com.netease.kaola.service.RoleBiz;
import com.netease.kaola.service.UserBiz;
import org.apache.ibatis.annotations.Param;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String login() {
        return "login";
    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String doLogin(User user, Model model, HttpSession session) {
        LOGGER.error("进入login");
        String username = user.getUsername();
        String password = user.getPassword();
        LOGGER.info("username => " + username);
        LOGGER.info("password => " + password);
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        Subject subject = SecurityUtils.getSubject();
        String msg = null;
        try {
            subject.login(token);
        } catch (UnknownAccountException e) {
            msg = e.getMessage();
        } catch (IncorrectCredentialsException e) {
            msg = "用户名或密码不正确)";
        } catch (LockedAccountException e) {
            msg = e.getMessage();
        }
        model.addAttribute("msg", msg);
        boolean isAuthenticated = subject.isAuthenticated();
        LOGGER.info("用户{}是否验证成功：{}", subject.getPrincipal(), isAuthenticated);
        if (isAuthenticated) {
            String principal = (String) subject.getPrincipal();
            session.setAttribute("username", principal);
            User currentUser = userBiz.findByUsername(username);
            //todo 前面没考虑好，只把username加入回话中，现在又直接加入了currentUser，回头只用currentUser
            session.setAttribute("currentUser", currentUser);
            if (subject.hasRole("seller")) {
                return "redirect:/seller";
            } else {
                return "redirect:/product";
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

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(Model model) {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        model.addAttribute("msg", "您已经退出登录");
        return "/login";
    }
}
