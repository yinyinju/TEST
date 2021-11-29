package com.example.demo.jwt;

import lombok.Data;
import org.apache.shiro.authc.AuthenticationToken;

@Data
public class JWTToken implements AuthenticationToken {
    private String token;

//    private String expireAt;

    public JWTToken(String token)
    {
        this.token=token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }


    @Override
    public Object getPrincipal()
    {
        return token;
    }

    @Override
    public Object getCredentials()
    {
        return token;
    }
}
