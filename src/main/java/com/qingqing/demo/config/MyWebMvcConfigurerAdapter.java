package com.qingqing.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MyWebMvcConfigurerAdapter
        implements WebMvcConfigurer
{
    @Autowired
    private LoginInterceptor loginInterceptor;

    public void addResourceHandlers(ResourceHandlerRegistry registry)
    {
        registry.addResourceHandler(new String[] { "/img/**" }).addResourceLocations(new String[] { "file:/webImg/img/" });
    }

    public void addInterceptors(InterceptorRegistry registry) {}
}
