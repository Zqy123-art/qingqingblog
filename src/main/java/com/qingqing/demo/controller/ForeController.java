//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.qingqing.demo.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qingqing.demo.dto.ArticleCommentDto;
import com.qingqing.demo.dto.ArticleDto;
import com.qingqing.demo.dto.ArticleWithPictureDto;
import com.qingqing.demo.entity.ArticleInfo;
import com.qingqing.demo.entity.CategoryInfo;
import com.qingqing.demo.entity.Comment;
import com.qingqing.demo.service.ArticleService;
import com.qingqing.demo.service.CommentService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping({"/api"})
public class ForeController {
    @Autowired
    private ArticleService articleService;
    @Autowired
    private CommentService commentService;

    public ForeController() {
    }

    @ApiOperation("获取所有文章")
    @ApiImplicitParam(
            name = "page",
            value = "页数",
            required = true,
            dataType = "String"
    )
    @GetMapping({"/article"})
    public String getAllArticleInfo(Model model, @RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum) {
        Page page = PageHelper.startPage(pageNum, 3);
        List<ArticleWithPictureDto> articleList = this.articleService.listAll();
        PageInfo pageInfo = new PageInfo(page.getResult());
        pageInfo.setList(articleList);
        model.addAttribute(pageInfo);
        return "articleInfo";
    }

    @ApiOperation("获取某分类下的文章")
    @ApiImplicitParam(
            name = "name",
            value = "文章名称",
            required = true,
            dataType = "String"
    )
    @ResponseBody
    @GetMapping({"/article/list/sort/{id}"})
    public List<ArticleInfo> getSortArticleInfo(@PathVariable Long id) {
        return null;
    }

    @ApiOperation("获取某id下的文章")
    @ApiImplicitParam(
            name = "name",
            value = "文章名称",
            required = true,
            dataType = "String"
    )
    @GetMapping({"/article/{id}"})
    public String getArticleInfoById(HttpServletRequest request, @PathVariable String id, Model model) {
        Map<String, String> datamap = new HashMap();
        String isvisited = (String)request.getSession().getAttribute("isvisited");
        ArticleDto articleDto = this.articleService.getOneById(id, datamap, isvisited);
        request.getSession().setAttribute("isvisited", "true");
        List<Comment> Comments = this.commentService.listAllArticleCommentById(id);
        List<ArticleCommentDto> commentDtos = new ArrayList();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Iterator var10 = Comments.iterator();

        while(var10.hasNext()) {
            Comment comment = (Comment)var10.next();
            ArticleCommentDto articleCommentDto = new ArticleCommentDto();
            String str = sdf.format(comment.getCreate_by());
            articleCommentDto.setCreat_by(str);
            articleCommentDto.setContent(comment.getContent());
            articleCommentDto.setName(comment.getName());
            commentDtos.add(articleCommentDto);
        }

        int len = Comments.size();
        model.addAttribute(articleDto);
        model.addAttribute("creatdate", datamap.get("creat"));
        model.addAttribute("modifydate", datamap.get("modify"));
        model.addAttribute("commentDtos", commentDtos);
        model.addAttribute("len", len);
        return "article";
    }

    @ApiOperation("显示需要修改的文章")
    @ApiImplicitParam(
            name = "name",
            value = "文章名称",
            required = true,
            dataType = "String"
    )
    @ResponseBody
    @GetMapping({"/articleModify/{id}"})
    public ArticleDto getArticleInfoById(@PathVariable String id) {
        String isvisited = "false";
        Map<String, String> datamap = new HashMap();
        ArticleDto articleDto = this.articleService.getOneById(id, datamap, isvisited);
        return articleDto;
    }

    @ApiOperation("获取所有分类信息")
    @ApiImplicitParam(
            name = "name",
            value = "文章名称",
            required = true,
            dataType = "String"
    )
    @ResponseBody
    @GetMapping({"/category/list"})
    public List<CategoryInfo> getAllCategoryInfo() {
        return null;
    }

    @ApiOperation("增加一条留言")
    @ApiImplicitParam(
            name = "name",
            value = "文章名称",
            required = true,
            dataType = "String"
    )
    @ResponseBody
    @PostMapping({"/comment/list"})
    public String addComment() {
        return null;
    }

    @ApiOperation("获取某一篇文章的评论信息")
    @ApiImplicitParam(
            name = "name",
            value = "文章名称",
            required = true,
            dataType = "String"
    )
    @ResponseBody
    @GetMapping({"/comment/article/{id}"})
    public Map<String, Object> getArticleComment(@PathVariable String id) {
        Map<String, Object> map = new HashMap();
        List<Comment> Comments = this.commentService.listAllArticleCommentById(id);
        List<ArticleCommentDto> commentDtos = new ArrayList();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Iterator var6 = Comments.iterator();

        while(var6.hasNext()) {
            Comment comment = (Comment)var6.next();
            ArticleCommentDto articleCommentDto = new ArticleCommentDto();
            sdf.format(comment.getCreate_by());
            articleCommentDto.setId(comment.getId());
            articleCommentDto.setContent(comment.getContent());
            articleCommentDto.setName(comment.getName());
            articleCommentDto.setIp(comment.getIp());
            articleCommentDto.setEmail(comment.getEmail());
            String Create_by = sdf.format(comment.getCreate_by());
            articleCommentDto.setCreat_by(Create_by);
            commentDtos.add(articleCommentDto);
        }

        map.put("data", commentDtos);
        map.put("code", 0);
        return map;
    }

    @ApiOperation("给文章增加一条留言")
    @ApiImplicitParam(
            name = "name",
            value = "文章名称",
            required = true,
            dataType = "String"
    )
    @ResponseBody
    @PostMapping({"/comment/article/{id}"})
    public String addArticleComment(@PathVariable Long id) {
        return null;
    }

    @ApiOperation("获取某一篇文章的评论信息")
    @ApiImplicitParam(
            name = "name",
            value = "文章名称",
            required = true,
            dataType = "String"
    )
    @ResponseBody
    @GetMapping({"/comment/list"})
    public List<CategoryInfo> getAllComment() {
        return null;
    }

    @ApiOperation("获取所有修改文章的信息")
    @ApiImplicitParam(
            name = "name",
            value = "文章名称",
            required = true,
            dataType = "String"
    )
    @ResponseBody
    @GetMapping({"/modify"})
    public Map<String, Object> getAllModifyArticleInfo(Model model) {
        List<ArticleWithPictureDto> articleList = this.articleService.listAll();
        Map<String, Object> map = new HashMap();
        map.put("code", 0);
        map.put("data", articleList);
        return map;
    }
}
