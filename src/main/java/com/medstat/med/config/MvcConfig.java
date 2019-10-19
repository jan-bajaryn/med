package com.medstat.med.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {


    @Bean
    public ObjectMapper getMapper(){
        return new ObjectMapper();
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**")
                .addResourceLocations("classpath:/static/");
    }

    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/index").setViewName("index");
        registry.addViewController("/").setViewName("index");

        registry.addViewController("/login").setViewName("shared/login");
        registry.addViewController("/registration").setViewName("shared/registration");
        registry.addViewController("/shared/search").setViewName("shared/search");

        registry.addViewController("/add_note").setViewName("add_note");
//        registry.addViewController("/profile_user").setViewName("profile_user");
        registry.addViewController("/admin_control").setViewName("admin_control");
    }

}
