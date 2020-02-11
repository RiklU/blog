package com.site.blog.my.core.config;

import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.MultipartConfigElement;
import java.io.File;

@Configuration
public class MyConfig {
    //    设置上传文件限制
    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory config = new MultipartConfigFactory();
        config.setMaxFileSize("10MB");
        config.setMaxRequestSize("100MB");
        String location = "/Users/yaolu/Desktop/working/practice/My-Blog-master/tmp/tomcat";
        File tmpFile = new File(location);
        if (!tmpFile.exists()) {
            tmpFile.mkdirs();
        }
        config.setLocation(location);
        return config.createMultipartConfig();
    }

}
