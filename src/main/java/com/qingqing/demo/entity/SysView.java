package com.qingqing.demo.entity;

import org.apache.ibatis.annotations.Mapper;

import java.util.Date;
@Mapper
public class SysView {
    private String id;

    private String ip;

    private Date create_by;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip == null ? null : ip.trim();
    }

    public Date getCreate_by() {
        return create_by;
    }

    public void setCreate_by(Date create_by) {
        this.create_by = create_by;
    }
}