//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.qingqing.demo.service;

import com.qingqing.demo.dto.ArticleCommentDto;
import com.qingqing.demo.entity.ArticleComment;
import com.qingqing.demo.entity.ArticleCommentExample;
import com.qingqing.demo.entity.Comment;
import com.qingqing.demo.entity.CommentExample;
import com.qingqing.demo.entity.ArticleCommentExample.Criteria;
import com.qingqing.demo.mapper.ArticleCommentMapper;
import com.qingqing.demo.mapper.CommentMapper;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

@Service("CommentService")
public class CommentServiceImpl implements CommentService {
    @Resource
    private ArticleCommentMapper articleCommentMapper;
    @Resource
    private CommentMapper commentMapper;

    public CommentServiceImpl() {
    }

    public void addComment(Comment comment) {
    }

    public void addArticleComment(ArticleCommentDto articleCommentDto) {
        Date date = new Date();
        Comment comment = new Comment();
        comment.setId(articleCommentDto.getId());
        comment.setContent(articleCommentDto.getContent());
        comment.setCreate_by(date);
        comment.setEmail(articleCommentDto.getEmail());
        comment.setIp(articleCommentDto.getIp());
        comment.setName(articleCommentDto.getName());
        comment.setIs_effective(articleCommentDto.getEffective());
        this.commentMapper.insert(comment);
        ArticleComment articleComment = new ArticleComment();
        String ArticleCommentUuid = UUID.randomUUID().toString().replace("-", "").toLowerCase();
        articleComment.setId(ArticleCommentUuid);
        articleComment.setArticle_id(articleCommentDto.getArticleId());
        articleComment.setComment_id(articleCommentDto.getId());
        articleComment.setCreate_by(date);
        articleComment.setIs_effective(articleCommentDto.getEffective());
        this.articleCommentMapper.insert(articleComment);
    }

    public void deleteCommentById(String id) {
        this.commentMapper.deleteByPrimaryKey(id);
    }

    public void deleteArticleCommentById(String id) {
        ArticleCommentExample articleCommentExample = new ArticleCommentExample();
        Criteria criteria = articleCommentExample.createCriteria();
        criteria.andComment_idEqualTo(id);
        ArticleComment articleComment = (ArticleComment)this.articleCommentMapper.selectByExample(articleCommentExample).get(0);
        this.articleCommentMapper.deleteByPrimaryKey(articleComment.getId());
    }

    public List<Comment> listAllComment() {
        CommentExample commentExample = new CommentExample();
        commentExample.setOrderByClause("create_by desc");
        List<Comment> comments = this.commentMapper.selectByExample(commentExample);
        return comments;
    }

    public List<Comment> listAllArticleCommentById(String id) {
        ArticleCommentExample ArticlecommentExample = new ArticleCommentExample();
        List<Comment> comments = new ArrayList();
        Criteria criteria = ArticlecommentExample.createCriteria();
        criteria.andArticle_idEqualTo(id);
        List<ArticleComment> Articlecomments = this.articleCommentMapper.selectByExample(ArticlecommentExample);
        List<String> commentID = new ArrayList();
        Iterator var7 = Articlecomments.iterator();

        while(var7.hasNext()) {
            ArticleComment articlecomment = (ArticleComment)var7.next();
            commentID.add(articlecomment.getComment_id());
        }

        var7 = commentID.iterator();

        while(var7.hasNext()) {
            String commentid = (String)var7.next();
            CommentExample commentExample = new CommentExample();
            com.qingqing.demo.entity.CommentExample.Criteria criteria1 = commentExample.createCriteria();
            criteria1.andIdEqualTo(commentid);
            Comment comment = (Comment)this.commentMapper.selectByExample(commentExample).get(0);
            comments.add(comment);
        }

        Collections.sort(comments, new Comparator<Comment>() {
            public int compare(Comment o1, Comment o2) {
                if (o1.getCreate_by().compareTo(o2.getCreate_by()) == 1) {
                    return -1;
                } else {
                    return o1.getCreate_by().compareTo(o2.getCreate_by()) == -1 ? 1 : 0;
                }
            }
        });
        return comments;
    }
}
