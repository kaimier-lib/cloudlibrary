package com.ybvtc.controller;

import com.ybvtc.common.Result;
import com.ybvtc.domain.dto.LoginDTO;
import com.ybvtc.domain.entity.User;
import com.ybvtc.service.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    public String login(@Valid LoginDTO loginDTO, HttpSession session,
                        RedirectAttributes redirectAttributes) {
        Result<User> result = userService.validateUser(loginDTO);
        if(result.isSuccess()==true){
            session.setAttribute("user",result.getData());
            return "redirect:/main";
        }

        redirectAttributes.addFlashAttribute("message",result.getMessage());
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
