package com.example.demo.shrio;

import com.example.demo.entity.Menu;
import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import com.example.demo.exception.TokenTimeoutException;
import com.example.demo.jwt.JWTToken;
import com.example.demo.jwt.JWTUtil;
import com.example.demo.service.MenuService;
import com.example.demo.service.RoleService;
import com.example.demo.service.UserService;
import com.example.demo.util.PasswordUitl;
import com.example.demo.util.RedisUtil;
import com.example.demo.vo.UserDTO;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.*;

@Configuration
public class UserRealm extends AuthorizingRealm {

    private final static  String PREFIX_USER_TOKEN="prefix_user_token";
    private final static long EXPIRE_TIME=30 *60 * 1000;
    @Autowired
    private UserService userService;

    @Autowired
    private MenuService menuService;

    @Autowired
    private RedisUtil redisUtil;


    private  final  static Logger log= LoggerFactory.getLogger(UserRealm.class);

    @Override
    public boolean supports(AuthenticationToken token)
    {
        return token instanceof JWTToken;
    }
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection)
    {

        log.info("---------shiro 授权-----------");
        SimpleAuthorizationInfo authorizationInfo=new SimpleAuthorizationInfo();
        String userName= JWTUtil.getUserName(principalCollection.toString());
        UserDTO userDTO=userService.getRoleMenuByName(userName);
        //根据用户名获取用户角色和权限信息
        List<Role> roleList=userDTO.getRoleList();
        authorizationInfo.addRole(roleList.get(0).getName());
        List<Menu> menuList=menuService.roleMenuByRoleId(roleList.get(0).getId());
        for(Menu menu:menuList)
        {
            authorizationInfo.addStringPermission(menu.getPerms());
        }
        return authorizationInfo;
    }


    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationTokentoken) throws AuthenticationException {

        log.info("----------------shiro认证--------------");
        String token = (String) authenticationTokentoken.getCredentials();
        String userName = JWTUtil.getUserName(token);
        if (userName == null) {
            throw new AuthenticationException("token非法无效");
        }

        User user = userService.findByName(userName);
        if (user == null) {
            throw new AuthenticationException("用户不存在");
        }
        String passwordSalt= PasswordUitl.encryptPassword(user.getPassword(),user.getSalt());
//        if (!JWTUtil.verify(token, userName, user.getPassword())) {
//            throw new AuthenticationException("用户名或者密码出错");
//        }
        if(!jwtTokenRefresh(token,userName,passwordSalt))
        {
            throw new AuthenticationException("Token失效请重新登录");
        }
        try
        {
            userName = JWTUtil.getUserName(token);
        } catch (Exception e) {
            throw new AuthenticationException("token非法，不是规范的token");
        }
        return new SimpleAuthenticationInfo(token, token, "my_realm");
    }

    public boolean jwtTokenRefresh(String token,String uesrName,String passwordSalt)
    {
        String cacheToken=String.valueOf(redisUtil.get(PREFIX_USER_TOKEN+"-"+uesrName));
        if(StringUtils.isBlank(cacheToken))
        {
            if(!JWTUtil.verify(cacheToken,uesrName,passwordSalt))
            {
                String newToken=JWTUtil.sign(uesrName,passwordSalt);
                redisUtil.set(PREFIX_USER_TOKEN+"-"+uesrName,newToken);
                redisUtil.expire(PREFIX_USER_TOKEN+"-"+uesrName,EXPIRE_TIME/1000);
            }
            else
            {
                redisUtil.set(PREFIX_USER_TOKEN+"-"+uesrName,cacheToken);
                redisUtil.expire(PREFIX_USER_TOKEN+"-"+uesrName,EXPIRE_TIME/1000);
            }
            return true;
        }
        return false;
    }

}
