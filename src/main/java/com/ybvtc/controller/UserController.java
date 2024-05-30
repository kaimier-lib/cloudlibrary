package com.ybvtc.controller;

import com.ybvtc.domain.User;
import com.ybvtc.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {
    @Autowired
    private UserService userService;
    // 跳转到登录页面
    @GetMapping("/login")
    public String login() {
        return "login";
    }
    // 接收登录表单请求
    @PostMapping("/login")
    public String login(User inputUser, HttpSession session) {
        User user=userService.getUser(inputUser);
        System.out.println(user);
        if(user!=null){
            session.setAttribute("user",user);
            return "redirect:/main";
        }
        return "redirect:/login";
    }

    // 跳转到主页面
    @GetMapping("/main")
    public String main() {
        return "main";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("user");
        return "login";
    }


}
