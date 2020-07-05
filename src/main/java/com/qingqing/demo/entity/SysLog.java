package com.qingqing.demo.entity;

import org.apache.ibatis.annotations.Mapper;

import java.util.Date;
@Mapper
public class SysLog {
    private Long id;

    private String ip;

    private Date creat_by;

    private String remark;

    private String operate_url;

    private String operate_by;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip == null ? null : ip.trim();
    }

    public Date getCreat_by() {
        return creat_by;
    }

    public void setCreat_by(Date creat_by) {
        this.creat_by = creat_by;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getOperate_url() {
        return operate_url;
    }

    public void setOperate_url(String operate_url) {
        this.operate_url = operate_url == null ? null : operate_url.trim();
    }

    public String getOperate_by() {
        return operate_by;
    }

    public void setOperate_by(String operate_by) {
        this.operate_by = operate_by == null ? null : operate_by.trim();
    }
}