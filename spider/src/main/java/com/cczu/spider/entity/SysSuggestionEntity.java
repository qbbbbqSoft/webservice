package com.cczu.spider.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "sys_suggestion")
public class SysSuggestionEntity {


    @Column(name = "ID")
    private Long id;

    private String email;

    private String content;

    @Column(name = "nickName")
    private String nickname;

    @Column(name = "createDate")
    private Date createdate;


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }
}
