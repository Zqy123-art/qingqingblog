//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.qingqing.demo.controller;

import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {
    public LoginController() {
    }

    @RequestMapping({"login"})
    public String gologin() {
        return "login";
    }

    @RequestMapping({"exit"})
    public String exitlogin(HttpSession session) {
        session.invalidate();
        return "login";
    }

    @PostMapping({"/login"})
    public String login(@RequestParam("username") String username, @RequestParam("password") String password, Model model, HttpSession session) {
        if (!StringUtils.isEmpty(username) && "123".equals(password)) {
            session.setAttribute("userName", username);
            return "redirect:modify";
        } else {
            session.invalidate();
            model.addAttribute("msg", "用户名密码错误");
            return "login";
        }
    }
}
