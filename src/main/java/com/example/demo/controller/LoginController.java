package com.example.demo.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.commonResult.CommonResult;
import com.example.demo.entity.Menu;
import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import com.example.demo.jwt.JWTToken;
import com.example.demo.jwt.JWTUtil;
import com.example.demo.jwt.PermissionProperyies;
import com.example.demo.service.MenuService;
import com.example.demo.service.RoleService;
import com.example.demo.service.UserService;
import com.example.demo.util.DateUtil;
import com.example.demo.util.PasswordUitl;
import com.example.demo.util.RedisUtil;
import com.example.demo.vo.LoginBean;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import javax.jws.soap.SOAPBinding;
import java.io.UnsupportedEncodingException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class LoginController {

    private final static  String PREFIX_USER_TOKEN="prefix_user_token";
    private final static long EXPIRE_TIME=30 *60 * 1000;
    @Autowired
    private UserService service;
    @Autowired
    private RedisUtil redisUtil;



    private final static Logger log= LoggerFactory.getLogger(LoginController.class);


    @RequestMapping(value = "/login",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")

    public CommonResult login(@RequestBody LoginBean loginBean) throws UnsupportedEncodingException {
        log.info("-------------开始登录--------------");
        String userName = loginBean.getName();
        String userPassword = loginBean.getPassword();
        if(org.apache.commons.lang3.StringUtils.isBlank(userName )|| org.apache.commons.lang3.StringUtils.isBlank(userPassword))
        {
            return CommonResult.failed("用户和密码不可以为空");
        }
        User user = service.findByName(userName);
        if (user == null)
        {
            return CommonResult.failed("用户不存在");
        }
        String passwordWithSalt = PasswordUitl.encryptPassword(userPassword, user.getSalt());
        if (!org.apache.commons.lang3.StringUtils.equals(user.getPassword(), passwordWithSalt)) {
            return CommonResult.failed("密码错误");
        }
        user.setLastUpdateTime(new Date());
        service.modifUser(user);
        String token=JWTUtil.sign(userName,passwordWithSalt);
        redisUtil.set(PREFIX_USER_TOKEN+"-"+userName,token);
        redisUtil.expire(PREFIX_USER_TOKEN+"-"+userName,EXPIRE_TIME/1000);
        return CommonResult.success(token,"登录成功");
    }



    @RequestMapping("/test")
    @RequiresRoles(value = {"admin"})
    public CommonResult getSercret()
  {
      String secretContent = "期中考试的答案";
      return CommonResult.success(secretContent);
  }
    @RequestMapping(value = "/logout",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    public CommonResult logout()
    {
        return CommonResult.success("登出成功");
    }
}


