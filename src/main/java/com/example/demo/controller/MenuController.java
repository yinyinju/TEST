package com.example.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.commonResult.CommonResult;
import com.example.demo.entity.Menu;
import com.example.demo.service.MenuService;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @RequestMapping(value = "/list",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public CommonResult getMenuList(@RequestBody JSONObject jsonObject)
    {
        int roleId=jsonObject.getInteger("id");
        List<Menu> menuList=menuService.roleMenuByRoleId(roleId);
        if(menuList!=null)
        {
            return CommonResult.success(menuList,"操作成功");
        }
        else
        {
            return CommonResult.failed("失败");
        }
    }

}
