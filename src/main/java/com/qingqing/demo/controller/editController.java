package com.qingqing.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class editController {
    public editController() {
    }

    @RequestMapping({"/edit"})
    public String edit() {
        return "edit";
    }

    @RequestMapping({"/modify"})
    public String modify() {
        return "modify";
    }

    @RequestMapping({"/comment"})
    public String comment() {
        return "comment";
    }
}
