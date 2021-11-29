package com.example.demo.jwt;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "my.shiro")
public class PermissionProperyies {
    private String annonUrl;

    private long jwtTimeOut;

}
