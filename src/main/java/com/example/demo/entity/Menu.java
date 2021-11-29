package com.example.demo.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Menu implements  Serializable {

    private Integer id;

    private String createBy;


    private Date createTime;

    private String lastUpdateBy;

    private Date lastUpdateTime;
    private Integer parentId;

    private String name;

    private String ulr;

    private String perms;

    private Integer type;

    private String icon;
    private  Integer orderNum;

    private Integer delFlg;

    private List<Menu> children;

    public Menu()
    {

    }

    public Menu(Integer id, String createBy, Date createTime, String lastUpdateBy, Date lastUpdateTime, Integer parentId, String name, String ulr, String perms, Integer type, String icon, Integer orderNum, Integer delFlg) {
        this.id = id;
        this.createBy = createBy;
        this.createTime = createTime;
        this.lastUpdateBy = lastUpdateBy;
        this.lastUpdateTime = lastUpdateTime;
        this.parentId = parentId;
        this.name = name;
        this.ulr = ulr;
        this.perms = perms;
        this.type = type;
        this.icon = icon;
        this.orderNum = orderNum;
        this.delFlg = delFlg;
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

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUlr() {
        return ulr;
    }

    public void setUlr(String ulr) {
        this.ulr = ulr;
    }

    public String getPerms() {
        return perms;
    }

    public void setPerms(String perms) {
        this.perms = perms;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    public Integer getDelFlg() {
        return delFlg;
    }

    public void setDelFlg(Integer delFlg) {
        this.delFlg = delFlg;
    }

    public List<Menu> getChildren() {
        return children;
    }

    public void setChildren(List<Menu> children)
    {
        this.children=children;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "id=" + id +
                ", createBy='" + createBy + '\'' +
                ", createTime=" + createTime +
                ", lastUpdateBy='" + lastUpdateBy + '\'' +
                ", lastUpdateTime=" + lastUpdateTime +
                ", parentId=" + parentId +
                ", name='" + name + '\'' +
                ", ulr='" + ulr + '\'' +
                ", perms='" + perms + '\'' +
                ", type=" + type +
                ", icon='" + icon + '\'' +
                ", orderNum=" + orderNum +
                ", delFlg=" + delFlg +
                '}';
    }
}
