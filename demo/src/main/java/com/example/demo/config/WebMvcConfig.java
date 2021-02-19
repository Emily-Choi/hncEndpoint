package com.example.demo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
	@Value("${file.upload-dir}")
    String fDir;
	
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/documents/**")
      
//                .addResourceLocations("file:/DATA/video/");     } //리눅스 root에서 시작하는 폴더 경로
        .addResourceLocations("file:" + fDir);
    	//윈도우일 경우 아래처럼 사용하면 된다.
    	//addResourceLocations("file:///D:/DATA/video/");
}


/*
@Configuration
public class WebMvcConfiguration extends WebMvcConfigurationSupport {
 
    private static final String CLASSPATH_RESOURCE_LOCATIONS = "classpath:/static/";
 
    @Value("${resource.uploads.root}")
    private String uploadsRoot;
    
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/uploads/**").addResourceLocations(uploadsRoot).setCachePeriod(31536000);
        registry.addResourceHandler("/assets/**").addResourceLocations(CLASSPATH_RESOURCE_LOCATIONS+"assets/").setCachePeriod(31536000);
        registry.addResourceHandler("/vendor/**").addResourceLocations(CLASSPATH_RESOURCE_LOCATIONS+"vendor/").setCachePeriod(31536000);
        registry.addResourceHandler("/html/**").addResourceLocations(CLASSPATH_RESOURCE_LOCATIONS+"html/").setCachePeriod(31536000);
    } */
 
}