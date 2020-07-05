package com.qingqing.demo;

import com.qingqing.demo.dto.ArticleDto;
import com.qingqing.demo.service.ArticleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class test {


    @RequestMapping("/article")
    public String test(){
        return "article";
    }
    @RequestMapping("/test")
    public String test1(){
        return "test";
    }
}
