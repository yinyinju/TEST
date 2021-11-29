package com.example.demo.util;

import com.example.demo.entity.User;
import org.apache.shiro.UnavailableSecurityManagerException;
import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;

public class PasswordUitl {

    //盐生成
    private static RandomNumberGenerator randomNumberGenerator=new SecureRandomNumberGenerator();

    //加密算法
    private static final String algorithName="md5";

    //加密算法运算次数

    private static  final  int hashIterations=2;

    /**
     * 使用盐加密用户密码
     * @param user
     */

    public static void encryptPassword(User user)
    {
        //随机盐
        String salt=randomNumberGenerator.nextBytes().toString();
        user.setSalt(salt);

        //注册用户的密码通过散列算法替换成一个不可逆的密码存进数据，使用过程运用了盐
        String newPassword=new SimpleHash(algorithName,user.getPassword(),salt,hashIterations).toString();
        user.setPassword(newPassword);
    }

    public static String encryptPassword(String password,String salt)
    {
        String newPassword=new SimpleHash(algorithName,password,salt,hashIterations).toString();
        return newPassword;
    }
}
