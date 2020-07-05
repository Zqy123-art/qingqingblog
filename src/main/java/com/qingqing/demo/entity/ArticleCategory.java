package com.qingqing.demo.entity;

import org.apache.ibatis.annotations.Mapper;

import java.util.Date;
@Mapper
public class ArticleCategory {
    private String id;

    private String sort_id;

    private String article_id;

    private Date create_by;

    private Date modified_by;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getSort_id() {
        return sort_id;
    }

    public void setSort_id(String sort_id) {
        this.sort_id = sort_id == null ? null : sort_id.trim();
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

    public Date getModified_by() {
        return modified_by;
    }

    public void setModified_by(Date modified_by) {
        this.modified_by = modified_by;
    }
}