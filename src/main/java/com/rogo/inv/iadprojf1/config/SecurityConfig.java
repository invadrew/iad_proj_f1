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
import org.springframework.security.crypto.password.Md4PasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    DataSource dataSource;

    @Autowired
    SecurityHandler securityHandler;

    @Autowired
    private UserServiceImpl userService;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new Md4PasswordEncoder();
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
                .antMatchers("/admin").hasAuthority("ADMIN")
                .antMatchers("/add_detail").hasAuthority("CONSTRUCTOR")
                .antMatchers("/**").authenticated()
                .anyRequest().permitAll()
                .and();

        http.formLogin()
                .loginPage("/")
                //Action с формы
                .loginProcessingUrl("/security_check")
                // указываем URL при неудачном логине (нет)

                .successHandler(securityHandler)

                //.defaultSuccessUrl("/profile")

                // Указываем параметры логина и пароля с формы логина
                .usernameParameter("uN")
                .passwordParameter("uP")
                // даем доступ к форме логина всем
                .permitAll();

        http.logout()
                .permitAll()
                // URL логаута
                .logoutUrl("/logout")
                // URL при удачном логауте
                .logoutSuccessUrl("/")
                .invalidateHttpSession(true);

    }

}
