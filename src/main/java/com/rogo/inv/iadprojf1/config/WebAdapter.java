package com.rogo.inv.iadprojf1.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableWebMvc
public class WebAdapter extends WebMvcConfigurerAdapter {

    @Override
    public void addResourceHandlers(final ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/WEB-INF/jsp/**").addResourceLocations("/WEB-INF/jsp/");
        registry.addResourceHandler("/pictures/**").addResourceLocations("file:/home/dell/IdeaProjects/iad_proj_f1/src/main/resources/pictures/");
        registry.addResourceHandler("/styles/**").addResourceLocations("/WEB-INF/styles/");
        registry.addResourceHandler("/scripts/**").addResourceLocations("/WEB-INF/scripts/");
    }

}