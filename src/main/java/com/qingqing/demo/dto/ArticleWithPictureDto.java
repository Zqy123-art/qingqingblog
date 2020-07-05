//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.qingqing.demo.dto;

public class ArticleWithPictureDto {
    private String id;
    private String title;
    private String summary;
    private Boolean isTop;
    private Integer traffic;
    private String date;
    private String articlePictureId;
    private String pictureUrl;

    public ArticleWithPictureDto() {
    }

    public String getDate() {
        return this.date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return this.summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public Boolean getTop() {
        return this.isTop;
    }

    public void setTop(Boolean top) {
        this.isTop = top;
    }

    public Integer getTraffic() {
        return this.traffic;
    }

    public void setTraffic(Integer traffic) {
        this.traffic = traffic;
    }

    public String getArticlePictureId() {
        return this.articlePictureId;
    }

    public void setArticlePictureId(String articlePictureId) {
        this.articlePictureId = articlePictureId;
    }

    public String getPictureUrl() {
        return this.pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }
}
