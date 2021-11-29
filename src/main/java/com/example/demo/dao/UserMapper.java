package com.example.demo.dao;

import com.example.demo.entity.User;
import org.apache.ibatis.annotations.Param;

import javax.jws.soap.SOAPBinding;
import java.util.List;

public interface UserMapper {
   User findByName(@Param("userName")String userName);

   int updateById(User user);

   List<User> getAllUserPage(@Param("pageNum") int pageNum,@Param("pageSize")int pageSize);

   User insert(User user);

   boolean deleteById(@Param("userId") int userId);

   User findByUserId(@Param("userId") int userId);
}
