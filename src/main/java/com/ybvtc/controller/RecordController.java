package com.ybvtc.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/record")
public class RecordController {

    @GetMapping("/searchRecords")
    public String searchRecords(Record inputRecord, Model model, HttpSession session) {

        // 返回视图名称
        return "record";
    }
}
