package com.example.demo.service;

import com.example.demo.dao.RoleMapper;
import com.example.demo.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.util.List;

@Service
public class RoleService {
    @Autowired

    private RoleMapper roleMapper;

    public List<Role> findRoleByUserId(int userId)
    {
        return roleMapper.findRoleByUserId(userId);
    }

}
