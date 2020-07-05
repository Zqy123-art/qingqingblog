package com.qingqing.demo.entity;

import org.apache.ibatis.annotations.Mapper;

import java.util.Date;
@Mapper
public class ArticleInfo {
    private String id;

    private String title;

    private String summary;

    private Boolean is_top;

    private Integer traffic;

    private Date create_by;

    private Date modified_by;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary == null ? null : summary.trim();
    }

    public Boolean getIs_top() {
        return is_top;
    }

    public void setIs_top(Boolean is_top) {
        this.is_top = is_top;
    }

    public Integer getTraffic() {
        return traffic;
    }

    public void setTraffic(Integer traffic) {
        this.traffic = traffic;
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