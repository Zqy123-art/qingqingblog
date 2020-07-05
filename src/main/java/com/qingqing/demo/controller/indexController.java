//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.qingqing.demo.controller;

import com.qingqing.demo.dto.ArticleWithPictureDto;
import com.qingqing.demo.service.ArticleService;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class indexController {
    @Autowired
    private ArticleService articleService;

    public indexController() {
    }

    @RequestMapping({"/"})
    public String index(HttpServletRequest request, Model model) {
        List<ArticleWithPictureDto> articleList = this.articleService.listAll();
        List<ArticleWithPictureDto> newArticleList = new ArrayList();
        if (articleList.size() != 0) {
            int i;
            if (articleList.size() < 5) {
                for(i = 0; i < articleList.size(); ++i) {
                    newArticleList.add(articleList.get(i));
                }
            } else {
                for(i = 0; i < 5; ++i) {
                    newArticleList.add(articleList.get(i));
                }
            }
        }

        model.addAttribute("newArticleList", newArticleList);
        return "index";
    }
}
