package com.rogo.inv.iadprojf1.config;

import com.rogo.inv.iadprojf1.service.impl.UserServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.PathResourceResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@EnableWebMvc
@Configuration
@ComponentScan("com.rogo.inv.iadprojf1.controller")
public class WebConfig  implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("MainPage");
        registry.addViewController("/profile").setViewName("UserProfilePage");
        registry.addViewController("/ranks").setViewName("RanksPage");
        registry.addViewController("/admin").setViewName("AdminPage");
        registry.addViewController("/sponsor").setViewName("SponsorProfilePage");
        registry.addViewController("/team").setViewName("TeamProfilePage");
        registry.addViewController("/race-reg").setViewName("RaceRegistrationPage");
        registry.addViewController("/race-res").setViewName("RaceResultsPage");
        registry.addViewController("/search").setViewName("SearchPage");
        registry.addViewController("/garage").setViewName("GaragePage");
        registry.addViewController("/add_detail").setViewName("DetailAddPage");
        registry.addViewController("/raceTime-mechanic").setViewName("RacetimeMechanicPage");
        registry.addViewController("/raceTime-racer").setViewName("RacetimeRacerPage");
    }

    @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver bean = new InternalResourceViewResolver();

        bean.setViewClass(JstlView.class);
        bean.setPrefix("/WEB-INF/jsp/");
        bean.setSuffix(".jsp");

        return bean;
    }

    @Bean
    public UserDetailsService getUserDetailsService(){
        return new UserServiceImpl();
    }

    @Bean(name = "multipartResolver")
    public CommonsMultipartResolver multipartResolver() {
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
        multipartResolver.setMaxUploadSize(100000);
        return new CommonsMultipartResolver();
    }

}
