package com.rogo.inv.iadprojf1.config;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.context.support.GenericWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

public class WebInitializer implements WebApplicationInitializer {

    @Override
    public void onStartup(final ServletContext sc) throws ServletException {

        AnnotationConfigWebApplicationContext root =
                new AnnotationConfigWebApplicationContext();

        root.scan("com.rogo.inv.iadprojf1.controller");
        //root.register(AppConfig.class);
        root.register(SecurityConfig.class);

        sc.addListener(new ContextLoaderListener(root));

        AnnotationConfigWebApplicationContext dispatcherContext = new AnnotationConfigWebApplicationContext();
        dispatcherContext.register(WebConfig.class);

        ServletRegistration.Dynamic authServlet =
                sc.addServlet("Auth", new DispatcherServlet(new GenericWebApplicationContext()));
        authServlet.setLoadOnStartup(1);
        authServlet.addMapping("/");
        authServlet.addMapping("/MainPage");

        ServletRegistration.Dynamic uprofileServlet =
                sc.addServlet("User Profile", new DispatcherServlet(new GenericWebApplicationContext()));
        uprofileServlet.setLoadOnStartup(1);
        uprofileServlet.addMapping("/profile");
        uprofileServlet.addMapping("/UserProfilePage");


        ServletRegistration.Dynamic ranksServlet =
                sc.addServlet("User Profile", new DispatcherServlet(new GenericWebApplicationContext()));
        ranksServlet.setLoadOnStartup(1);
        ranksServlet.addMapping("/ranks");
        ranksServlet.addMapping("/RanksPage");
    }
}
