package com.ybvtc.controller;

import com.ybvtc.domain.Record;
import com.ybvtc.service.RecordService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/record")
public class RecordController {
    @Autowired
    private RecordService recordService;

    @GetMapping("/searchRecords")
    public String searchRecords(Record inputRecord, Model model, HttpSession session) {
        recordService.serachRecords(inputRecord);
        // 返回视图名称
        return "record";
    }
}
