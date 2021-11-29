package com.example.demo.dao;

import com.example.demo.entity.Menu;
import com.example.demo.entity.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MenuMapper {
    List<Menu> findRolesMenuByRoleId(@Param("roleId") int roleId);

    List<Menu> findRolesMenuByFatherId(@Param("parentId") int parentId ,@Param("roleId") int roleId);
}
