package com.example.demo.vo;

import com.example.demo.entity.Menu;
import com.example.demo.entity.Role;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class UserDTO implements Serializable{
    private int id;

    private String name;

    private String password;

    private List<Role> roleList;

    private List<Menu> menuList;

    private String createBy;

    private Date createTime;

    private String lastUpdaeBy;

    private Date lastUpdateTime;

    private int status;

   private String email;

   private String mobile;

   private int delFlag;

   private String photo;

   public UserDTO()
   {

   }

    public UserDTO(int id, String name, String password, List<Role> roleList, List<Menu> menuList, String createBy, Date createTime, String lastUpdaeBy, Date lastUpdateTime, int status, String email, String mobile, int delFlag,String photo) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.roleList = roleList;
        this.menuList = menuList;
        this.createBy = createBy;
        this.createTime = createTime;
        this.lastUpdaeBy = lastUpdaeBy;
        this.lastUpdateTime = lastUpdateTime;
        this.status = status;
        this.email = email;
        this.mobile = mobile;
        this.delFlag = delFlag;
        this.photo=photo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public List<Role> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }

    public List<Menu> getMenuList() {
        return menuList;
    }

    public void setMenuList(List<Menu> menuList) {
        this.menuList = menuList;
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

    public String getLastUpdaeBy() {
        return lastUpdaeBy;
    }

    public void setLastUpdaeBy(String lastUpdaeBy) {
        this.lastUpdaeBy = lastUpdaeBy;
    }

    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
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

    public int getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(int delFlag) {
        this.delFlag = delFlag;
    }

    public void setPhoto(String photo)
    {
        this.photo=photo;
    }

    public String getPhoto() {
        return photo;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", roleList=" + roleList +
                ", menuList=" + menuList +
                ", createBy='" + createBy + '\'' +
                ", createTime=" + createTime +
                ", lastUpdaeBy='" + lastUpdaeBy + '\'' +
                ", lastUpdateTime=" + lastUpdateTime +
                ", status=" + status +
                ", email='" + email + '\'' +
                ", mobile='" + mobile + '\'' +
                ", delFlag=" + delFlag +
                ", photo="+photo+
                '}';
    }
}

