package com.example.demo.service;

import com.example.demo.dao.MenuMapper;
import com.example.demo.entity.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuService {
    @Autowired
    private MenuMapper menuMapper;

    public List<Menu> roleMenuByRoleId(int roleId)
    {
        return menuMapper.findRolesMenuByRoleId(roleId);
    }

    public List<Menu> findRolesMenuByFatherId(int fatherId,int roleId)
    {
        return menuMapper.findRolesMenuByFatherId(fatherId,roleId);
    }
}
