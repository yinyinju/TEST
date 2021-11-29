package com.example.demo.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.demo.entity.User;
import com.example.demo.util.SpringContextUtil;
import org.apache.commons.lang3.StringUtils;

import javax.print.DocFlavor;
import java.io.UnsupportedEncodingException;
import java.util.Date;

public class JWTUtil {
//    private static final long EXPIRE_TIME= SpringContextUtil.getBean(PermissionProperyies.class).getJwtTimeOut() * 1000;


    private static final long EXPIRE_TIME=30 *60 * 1000;

    /**
     * 检验token 是否正确
     *
     */
    public static boolean verify(String token,String userName,String secret)
    {
        try
        {
            Algorithm algorithm=Algorithm.HMAC256(secret);
            JWTVerifier verifier= JWT.require(algorithm)
                    .withClaim("userName",userName)
                    .build();
            //检验token
            verifier.verify(token);
            return true;
        }catch (Exception e)
        {
            System.out.println("token检验失败");
            return false;
        }
    }

    /**
     * 根据token获取用户名
     */
   public static String getUserName(String token)
   {
       try
       {
           DecodedJWT jwt=JWT.decode(token);
           return jwt.getClaim("userName").asString();
       }catch (JWTDecodeException e)
       {
           return null;
       }
   }

    /**
     * 生成签名
     *
     *
     */
    public static String sign(String userName,String secret)
    {
        try
        {
            Date date=new Date(System.currentTimeMillis()+EXPIRE_TIME);
            Algorithm algorithm=Algorithm.HMAC256(secret);
            String jwtString=JWT.create().withClaim("userName",userName)
                    .withExpiresAt(date)
                    .sign(algorithm);
            return jwtString;

        }catch(Exception e)
        {
            return null;
        }
    }
}

