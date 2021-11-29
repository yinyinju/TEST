package com.example.demo.util;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Data
@Component
@Configuration
@ConfigurationProperties(prefix = "rainbow.shiro")
public class RainbowProperites {
    //免认证URL
    private String annoUrl;

    //token默认有效时间为1天

    private long jwtTimeOut=86400L;
}
