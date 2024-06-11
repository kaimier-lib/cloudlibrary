package com.ybvtc.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ybvtc.domain.entity.Record;
import com.ybvtc.domain.entity.User;
import com.ybvtc.service.RecordService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/record")
public class RecordController {
    @Autowired
    private RecordService recordService;

    @GetMapping("/searchRecords")
    public String searchRecords(Record inputRecord, Model model, HttpSession session,
                                @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                                @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        if(pageNum == null) {
            pageNum = 1;
        }
        if (pageSize == null) {
            pageSize = 5;
        }
        User user = (User) session.getAttribute("user");
        IPage<Record> pageResult = recordService.serachRecords(inputRecord, user, pageNum, pageSize);
        model.addAttribute("pageResult", pageResult);
        model.addAttribute("search", inputRecord);
        model.addAttribute("pageNum", pageNum);
        model.addAttribute("gourl","/record/searchRecords");
        // 返回视图名称
        return "record";
    }
}
