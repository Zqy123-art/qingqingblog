//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.qingqing.demo.service;

import com.qingqing.demo.dto.ArticleCommentDto;
import com.qingqing.demo.entity.Comment;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public interface CommentService {
    void addComment(Comment comment);

    void addArticleComment(ArticleCommentDto articleCommentDto);

    void deleteCommentById(String id);

    void deleteArticleCommentById(String id);

    List<Comment> listAllComment();

    List<Comment> listAllArticleCommentById(String id);
}
