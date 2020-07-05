
package com.qingqing.demo.controller;

import com.qingqing.demo.dto.ArticleCommentDto;
import com.qingqing.demo.dto.ArticleDto;
import com.qingqing.demo.dto.ResultDto;
import com.qingqing.demo.service.ArticleService;
import com.qingqing.demo.service.CommentService;
import com.qingqing.demo.util.GetIpAddress;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import java.util.Map;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping({"/admin"})
public class BackController {
    @Autowired
    private ArticleService articleService;
    @Autowired
    private CommentService commentService;

    public BackController() {
    }

    @ApiOperation("增加分类信息")
    @ApiImplicitParam(
            name = "name",
            value = "分类名称",
            required = true,
            dataType = "String"
    )
    @ResponseBody
    @PostMapping({"/category"})
    public String addCategoryInfo(@RequestBody Map<String, String> all) {
        return null;
    }

    @ApiOperation("更新/编辑分类信息")
    @ApiImplicitParam(
            name = "id",
            value = "分类ID",
            required = true,
            dataType = "Long"
    )
    @PutMapping({"/category/{id}"})
    public String updateCategoryInfo(@PathVariable Long id) {
        return null;
    }

    @ApiOperation("删除分类信息")
    @ApiImplicitParam(
            name = "id",
            value = "分类ID",
            required = true,
            dataType = "Long"
    )
    @DeleteMapping({"/category/{id}"})
    public String deleteCategoryInfo(@PathVariable Long id) {
        return null;
    }

    @ApiOperation("获取某一条分类信息")
    @ApiImplicitParam(
            name = "name",
            value = "文章名称",
            required = true,
            dataType = "String"
    )
    @GetMapping({"/category/{id}"})
    public String getCategory(@PathVariable Long id) {
        return null;
    }

    @ApiOperation("增加一篇文章")
    @ApiImplicitParam(
            name = "name",
            value = "文章名称",
            required = true,
            dataType = "String"
    )
    @PostMapping({"/article"})
    @ResponseBody
    public ResultDto updateArticleInfo(@RequestBody Map<String, String> map) {
        String title = (String)map.get("title");
        String content = (String)map.get("content");
        String describe = (String)map.get("describe");
        String art_pic = (String)map.get("art_pic");
        String isTop = (String)map.get("istop");
        String artclass = (String)map.get("artclass");
        ArticleDto articleDto = new ArticleDto();
        String uuid = UUID.randomUUID().toString().replace("-", "").toLowerCase();
        articleDto.setId(uuid);
        articleDto.setTitle(title);
        articleDto.setSummary(describe);
        boolean top;
        if (isTop.equals("true")) {
            top = true;
        } else {
            top = false;
        }

        articleDto.setTop(top);
        articleDto.setTraffic(0);
        articleDto.setArticleContentId(articleDto.getId());
        articleDto.setContent(content);
        articleDto.setCategoryName(artclass);
        articleDto.setArticleCategoryId(articleDto.getCategoryId());
        articleDto.setArticlePictureId(articleDto.getId());
        articleDto.setPictureUrl(art_pic);
        this.articleService.addArticle(articleDto);
        ResultDto resultDto = new ResultDto();
        resultDto.setCode(200);
        resultDto.setMessages("发表成功");
        return resultDto;
    }

    @ApiOperation("更改文章题图信息")
    @ApiImplicitParam(
            name = "name",
            value = "文章名称",
            required = true,
            dataType = "String"
    )
    @PutMapping({"/article/picture/{id}"})
    public String updateArticlePictureInfo(@PathVariable Long id) {
        return null;
    }

    @ApiOperation("更改文章种类")
    @ApiImplicitParam(
            name = "name",
            value = "文章名称",
            required = true,
            dataType = "String"
    )
    @PutMapping({"/article/sort/{id}"})
    public String updateArticleSort(@PathVariable Long id) {
        return null;
    }

    @ApiOperation("删除文章")
    @ApiImplicitParam(
            name = "name",
            value = "文章名称",
            required = true,
            dataType = "String"
    )
    @ResponseBody
    @DeleteMapping({"/article/{id}"})
    public ResultDto deleteArticle(@PathVariable String id) {
        this.articleService.deleteArticleById(id);
        ResultDto resultDto = new ResultDto();
        resultDto.setCode(200);
        resultDto.setMessages("删除成功");
        return resultDto;
    }

    @ApiOperation("更改文章")
    @ApiImplicitParam(
            name = "name",
            value = "文章名称",
            required = true,
            dataType = "String"
    )
    @ResponseBody
    @PutMapping({"/article/{id}"})
    public ResultDto updateArticle(@PathVariable String id, @RequestBody Map<String, String> map) {
        String title = (String)map.get("title");
        String content = (String)map.get("content");
        String describe = (String)map.get("describe");
        String art_pic = (String)map.get("art_pic");
        String artclass = (String)map.get("artclass");
        Boolean istop;
        if (((String)map.get("istop")).equals("true")) {
            istop = true;
        } else {
            istop = false;
        }

        ArticleDto articleDto = new ArticleDto();
        articleDto.setContent(content);
        articleDto.setPictureUrl(art_pic);
        articleDto.setCategoryName(artclass);
        articleDto.setTraffic(0);
        articleDto.setTop(istop);
        articleDto.setId(id);
        articleDto.setArticleContentId(id);
        articleDto.setTitle(title);
        articleDto.setSummary(describe);
        this.articleService.updateArticle(articleDto);
        ResultDto resultDto = new ResultDto();
        resultDto.setCode(200);
        resultDto.setMessages("更新成功");
        return resultDto;
    }

    @ApiOperation("删除文章评论")
    @ApiImplicitParam(
            name = "name",
            value = "文章名称",
            required = true,
            dataType = "String"
    )
    @DeleteMapping({"/comment/article/{id}"})
    public String deleteArticleComment(@PathVariable Long id) {
        return null;
    }

    @ApiOperation("删除留言")
    @ApiImplicitParam(
            name = "name",
            value = "文章名称",
            required = true,
            dataType = "String"
    )
    @DeleteMapping({"/comment/{id}"})
    @ResponseBody
    public ResultDto deleteComment(@PathVariable String id) {
        this.commentService.deleteArticleCommentById(id);
        this.commentService.deleteCommentById(id);
        ResultDto resultDto = new ResultDto();
        resultDto.setCode(200);
        resultDto.setMessages("评论删除成功");
        return resultDto;
    }

    @ApiOperation("回复评论或者留言")
    @ApiImplicitParam(
            name = "name",
            value = "评论",
            required = true,
            dataType = "String"
    )
    @ResponseBody
    @PostMapping({"/Comment"})
    public ResultDto replyComment(@RequestBody Map<String, String> map, HttpServletRequest request) {
        String id = (String)map.get("id");
        String comment = (String)map.get("comment");
        String name = (String)map.get("name");
        String email = (String)map.get("email");
        String ip = GetIpAddress.getIpAddr(request);
        boolean effective = true;
        ArticleCommentDto articleCommentDto = new ArticleCommentDto();
        String CommentUuid = UUID.randomUUID().toString().replace("-", "").toLowerCase();
        articleCommentDto.setId(CommentUuid);
        articleCommentDto.setArticleId(id);
        articleCommentDto.setContent(comment);
        articleCommentDto.setEffective(effective);
        articleCommentDto.setEmail(email);
        articleCommentDto.setIp(ip);
        articleCommentDto.setName(name);
        this.commentService.addArticleComment(articleCommentDto);
        ResultDto resultDto = new ResultDto();
        resultDto.setMessages("评论成功");
        resultDto.setCode(200);
        return resultDto;
    }
}
