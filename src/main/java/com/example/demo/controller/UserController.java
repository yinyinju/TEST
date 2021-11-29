package com.example.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.commonResult.CommonResult;
import com.example.demo.dao.RoleMapper;
import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import com.example.demo.service.RoleService;
import com.example.demo.service.UserService;
import com.example.demo.util.PasswordUitl;
import com.example.demo.vo.LoginBean;
import com.example.demo.vo.UserDTO;
import com.github.pagehelper.PageInfo;
import org.apache.commons.io.FilenameUtils;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {

    private final static Logger log= LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Value("${photo.upload}")
    private String filePath;


    @RequestMapping(value = "/findAllUser",method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @RequiresRoles(logical = Logical.OR,value = {"admin","dev"})
    @RequiresPermissions("sys:user:view")
    public CommonResult getAllUser(@RequestBody JSONObject jsonObject)
    {

        log.info("-----------------分页获取所有用户-------------");
        int pageNum=jsonObject.getInteger("pageNum");
        int pageSize=jsonObject.getInteger("pageSize");
        PageInfo<User> userPageInfo=userService.findUser(pageNum,pageSize);
        if(userPageInfo!=null)
        {
            return CommonResult.success(userPageInfo,"操作成功");
        }
        else
        {
            return  CommonResult.failed("失败");
        }
    }

    @RequestMapping(value = "/changeUserInfo",method = RequestMethod.POST)
    @RequiresRoles(value = {"admin"})
    @RequiresPermissions("sys:user:edit")
    public CommonResult changeUserInfo(UserDTO userDTO, @RequestParam("userImage") MultipartFile userImage)
    {
        log.info("-------------------修改用户信息------------------");
        String url="http://localhost:8086";
        User user=userService.findByName(userDTO.getName());
        if(user==null)
        {
            return CommonResult.failed("用户不存在");
        }
        else
        {
            try
            {
                if(userImage!=null)
                {
                    String userImageName= UUID.randomUUID().toString()+"."+ FilenameUtils.getExtension(userImage.getOriginalFilename());
                    String urlPath="image"+ File.separator+userImageName;
                    File saveFile=new File(filePath,userImageName);
                    if(!saveFile.exists())
                    {
                        saveFile.mkdir();
                    }
                    userImage.transferTo(saveFile);
                    user.setPhoto(url+urlPath);
                }
                if(user.getPassword()!=null)
                {
                    user.setPassword(PasswordUitl.encryptPassword(userDTO.getPassword(),user.getSalt()));
                }
                userService.modifUser(user);
            }catch (IllegalStateException e)
            {
                return CommonResult.failed(e.getMessage());
            }catch (IOException e)
            {
                return CommonResult.failed("图像传输错误");
            }
            return CommonResult.success(null);
        }

    }

    @RequestMapping(value = "/delete",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    @RequiresRoles(value = {"admin"})
    @RequiresPermissions("sys:user:delete")
    public CommonResult deleteByUserId(@RequestBody JSONObject jsonObject)
    {
        log.info("-------------------删除用户------------------");
        int userId=jsonObject.getInteger("userId");
        if(userId<0)
        {
            return CommonResult.failed("请选择正确的userId");
        }
        else
        {
            User user=userService.getUserByUserId(userId);
            if(user==null)
            {
                return CommonResult.failed("该用户不存在");
            }
            userService.deleteUserByUserId(userId);
            return CommonResult.success(null);
        }
    }

}
