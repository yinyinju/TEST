package com.example.demo.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Role implements Serializable {

    private Integer id;

    private String createBy;

    private Date createTime;

    private String lastUpdateBy;

    private Date lastUpdateTime;
    private  String name;

    private String remark;

    private Integer delFlag;

    public Role()
    {

    }

    public Role(Integer id, String createBy, Date createTime, String lastUpdateBy, Date lastUpdateTime, String name, String remark, Integer delFlag) {
        this.id = id;
        this.createBy = createBy;
        this.createTime = createTime;
        this.lastUpdateBy = lastUpdateBy;
        this.lastUpdateTime = lastUpdateTime;
        this.name = name;
        this.remark = remark;
        this.delFlag = delFlag;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
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

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", createBy='" + createBy + '\'' +
                ", createTime=" + createTime +
                ", lastUpdateBy='" + lastUpdateBy + '\'' +
                ", lastUpdateTime=" + lastUpdateTime +
                ", name='" + name + '\'' +
                ", remark='" + remark + '\'' +
                ", delFlag=" + delFlag +
                '}';
    }
}
