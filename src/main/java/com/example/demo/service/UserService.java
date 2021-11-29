package com.example.demo.service;

import com.example.demo.commonResult.CommonResult;
import com.example.demo.dao.MenuMapper;
import com.example.demo.dao.RoleMapper;
import com.example.demo.dao.UserMapper;
import com.example.demo.entity.Menu;
import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import com.example.demo.vo.UserDTO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private MenuMapper menuMapper;


    public User findByName(String userName)
    {
        return userMapper.findByName(userName);
    }

    public int modifUser(User user)
    {
        return userMapper.updateById(user);
    }

    public PageInfo<User> findUser(int pageNum,int pageSize)
    {
        PageHelper.startPage(pageNum,pageSize);
        List<User> allUser=userMapper.getAllUserPage(pageNum,pageSize);
        return new PageInfo(allUser);
    }

    public UserDTO getRoleMenuByName(String name)
    {
        //根据用户名字查询用户角色
        User user=userMapper.findByName(name);
        UserDTO userDTO=new UserDTO();
        if(user!=null)
        {
            BeanUtils.copyProperties(user,userDTO);
            List<Role> roleList=roleMapper.findRoleByUserId(user.getId());
            if(roleList!=null)
            {
                userDTO.setRoleList(roleList);
                //查询权限信息
                List<Menu> parentMenuList=menuMapper.findRolesMenuByFatherId(0,user.getId());
                List<Menu> sonMenuList=null;
                List<Menu> sonsonMenuList=null;
                if(parentMenuList.size()>0)
                {
                    for (int i = 0, j = parentMenuList.size(); i < j; i++) {
                        sonMenuList = menuMapper.findRolesMenuByFatherId(parentMenuList.get(i).getId(), user.getId());
                        for (int k = 0, l = sonMenuList.size(); k < l; k++) {
                            sonsonMenuList = menuMapper.findRolesMenuByFatherId(sonMenuList.get(k).getId(), user.getId());
                            sonMenuList.get(k).setChildren(sonsonMenuList);
                        }
                        parentMenuList.get(i).setChildren(sonMenuList);
                    }
                    userDTO.setMenuList(parentMenuList);
                }
            }
            return userDTO;
        }
        else {
            return null;
        }
    }
    public void deleteUserByUserId(int userId)
    {
        userMapper.deleteById(userId);
    }

    public User getUserByUserId(int userId)
    {
        return userMapper.findByUserId(userId);
    }
}
