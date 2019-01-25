package com.rogo.inv.iadprojf1.config;


import com.rogo.inv.iadprojf1.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    DataSource dataSource;


    @Autowired
    private UserServiceImpl userService;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .passwordEncoder(passwordEncoder())
                .usersByUsernameQuery("select login, password, 1 from users where login=?")
                .authoritiesByUsernameQuery("select users.login, users.spec from users where users.login=?");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf()
                .disable()
                .authorizeRequests()
                .antMatchers("/pictures/**").permitAll()
                .antMatchers("/styles/**").permitAll()
                .antMatchers("/scripts/**").permitAll()
                .antMatchers("/**").authenticated()
                .anyRequest().permitAll()
                .and();

        http.formLogin()
                // указываем страницу с формой логина
                .loginPage("/")
                // указываем action с формы логина
                .loginProcessingUrl("/security_check")
                // указываем URL при неудачном логине
                .failureUrl("/")
                .defaultSuccessUrl("/profile")
                // Указываем параметры логина и пароля с формы логина
                .usernameParameter("uN")
                .passwordParameter("uP")
                // даем доступ к форме логина всем
                .permitAll();

    }

}
