package com.cczu.spider.entity;

import java.util.Date;
import javax.persistence.*;

@Entity
@Table(name = "sys_zone")
public class SysZoneEntity {

    @Column(name = "ID")
    private Long id;

    /**
     * 唯一的code
     */
    @Column(name = "ZoneCode")
    private String zonecode;

    /**
     * 第一个创建的人可以设置
     */
    @Column(name = "ZoneName")
    private String zonename;

    @Column(name = "CreateDate")
    private Date createdate;

    /**
     * @return ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取唯一的code
     *
     * @return ZoneCode - 唯一的code
     */
    public String getZonecode() {
        return zonecode;
    }

    /**
     * 设置唯一的code
     *
     * @param zonecode 唯一的code
     */
    public void setZonecode(String zonecode) {
        this.zonecode = zonecode;
    }

    /**
     * 获取第一个创建的人可以设置
     *
     * @return ZoneName - 第一个创建的人可以设置
     */
    public String getZonename() {
        return zonename;
    }

    /**
     * 设置第一个创建的人可以设置
     *
     * @param zonename 第一个创建的人可以设置
     */
    public void setZonename(String zonename) {
        this.zonename = zonename;
    }

    /**
     * @return CreateDate
     */
    public Date getCreatedate() {
        return createdate;
    }

    /**
     * @param createdate
     */
    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }
}