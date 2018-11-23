package com.cczu.spider.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "sys_index")
public class SysIndexEntity {
    private Long id;
    private String title;
    private String content;
    private Integer order;
    @JsonIgnore
    private Date createdate;
    @JsonIgnore
    private Date updatedate;
    @JsonIgnore
    private Boolean delstatus;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    public Date getUpdatedate() {
        return updatedate;
    }

    public void setUpdatedate(Date updatedate) {
        this.updatedate = updatedate;
    }

    public Boolean getDelstatus() {
        return delstatus;
    }

    public void setDelstatus(Boolean delstatus) {
        this.delstatus = delstatus;
    }
}
