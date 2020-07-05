//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.qingqing.demo.interceptor;

import com.qingqing.demo.dto.UserDto;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;

public class BackInterceptor implements HandlerInterceptor {
    private static String username = "zqy";
    private static String password = "793601044";

    public BackInterceptor() {
    }

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        boolean flag = true;
        UserDto user = (UserDto)request.getSession().getAttribute("user");
        if (null == user) {
            flag = false;
        } else if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
            flag = true;
        } else {
            flag = false;
        }

        return flag;
    }
}
