package com.qingqing.demo.entity;

import org.apache.ibatis.annotations.Mapper;

import java.util.Date;
@Mapper
public class ArticleContent {
    private String id;

    private String article_id;

    private Date create_by;

    private Date modifield_by;

    private String content;

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

    public Date getCreate_by() {
        return create_by;
    }

    public void setCreate_by(Date create_by) {
        this.create_by = create_by;
    }

    public Date getModifield_by() {
        return modifield_by;
    }

    public void setModifield_by(Date modifield_by) {
        this.modifield_by = modifield_by;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}