package com.example.demo.entity;

import java.io.Serializable;
import java.util.Date;

public class UserRole implements Serializable {

    private Integer id;

    private String createBy;

    private Date createTime;

    private String lastUpdateBy;

    private Date lastUpdateTime;

    private Integer userId;

    private Integer roleId;

    public UserRole(Integer id, String createBy, Date createTime, String lastUpdateBy, Date lastUpdateTime, Integer userId, Integer roleId) {
        this.id = id;
        this.createBy = createBy;
        this.createTime = createTime;
        this.lastUpdateBy = lastUpdateBy;
        this.lastUpdateTime = lastUpdateTime;
        this.userId = userId;
        this.roleId = roleId;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getLastUpdateBy() {
        return lastUpdateBy;
    }

    public void setLastUpdateBy(String lastUpdateBy) {
        this.lastUpdateBy = lastUpdateBy;
    }

    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    @Override
    public String toString() {
        return "UserRole{" +
                "id=" + id +
                ", createBy='" + createBy + '\'' +
                ", createTime=" + createTime +
                ", lastUpdateBy='" + lastUpdateBy + '\'' +
                ", lastUpdateTime=" + lastUpdateTime +
                ", userId=" + userId +
                ", roleId=" + roleId +
                '}';
    }
}
