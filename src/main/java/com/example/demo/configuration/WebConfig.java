package com.example.demo.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    public void addResourceHandles(ResourceHandlerRegistry registry)
    {
        //和页面有关的静态目录放在static目录下
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
        //上传的图片在D盘下的OTA目录下，访问路径如：http://localhsot:8086/
        //windows配置
        registry.addResourceHandler("/image/**").addResourceLocations("file:E:/project_resoures/shrio_jwt_file/image");

    }
}
