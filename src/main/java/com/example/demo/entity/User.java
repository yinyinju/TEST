package com.example.demo.entity;

import org.springframework.context.annotation.Profile;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class User  implements Serializable{

    private Integer id;

    private String createBy;

    private Date createTime;

    private String lastUpdateBy;

    private Date lastUpdateTime;
   private String name;

   private String password;

   private String salt;

   private String email;

   private String mobile;

   private Integer status;

   private Integer delFlag;

   private String photo;

   public User()
   {

   }

    public User(Integer id, String createBy, Date createTime, String lastUpdateBy, Date lastUpdateTime, String name, String password, String salt, String email, String mobile, Integer status, Integer delFlag,String photo) {
        this.id = id;
        this.createBy = createBy;
        this.createTime = createTime;
        this.lastUpdateBy = lastUpdateBy;
        this.lastUpdateTime = lastUpdateTime;
        this.name = name;
        this.password = password;
        this.salt = salt;
        this.email = email;
        this.mobile = mobile;
        this.status = status;
        this.delFlag = delFlag;
        this.photo=photo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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

    public String getPhoto() {
        return photo;
    }
    public void setPhoto(String photo)
    {
        this.photo=photo;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", createBy='" + createBy + '\'' +
                ", createTime=" + createTime +
                ", lastUpdateBy='" + lastUpdateBy + '\'' +
                ", lastUpdateTime=" + lastUpdateTime +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", salt='" + salt + '\'' +
                ", email='" + email + '\'' +
                ", mobile='" + mobile + '\'' +
                ", status=" + status +
                ", delFlag=" + delFlag +
                ", photo="+photo+
                '}';
    }
}
