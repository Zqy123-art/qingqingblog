package com.qingqing.demo.entity;

import org.apache.ibatis.annotations.Mapper;

import java.util.Date;
@Mapper
public class ArticleComment {
    private String id;

    private String article_id;

    private String comment_id;

    private Date create_by;

    private Boolean is_effective;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getArticle_id() {
        return article_id;
    }

    public void setArticle_id(String article_id) {
        this.article_id = article_id == null ? null : article_id.trim();
    }

    public String getComment_id() {
        return comment_id;
    }

    public void setComment_id(String comment_id) {
        this.comment_id = comment_id == null ? null : comment_id.trim();
    }

    public Date getCreate_by() {
        return create_by;
    }

    public void setCreate_by(Date create_by) {
        this.create_by = create_by;
    }

    public Boolean getIs_effective() {
        return is_effective;
    }

    public void setIs_effective(Boolean is_effective) {
        this.is_effective = is_effective;
    }
}