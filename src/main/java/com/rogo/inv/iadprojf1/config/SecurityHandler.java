package com.rogo.inv.iadprojf1.config;

import com.rogo.inv.iadprojf1.entity.User;
import com.rogo.inv.iadprojf1.service.UserService;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.internal.SessionFactoryBuilderImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.provider.HibernateUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.io.IOException;
import java.security.Principal;
import java.util.Set;

@Component
public class SecurityHandler implements AuthenticationSuccessHandler {

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Autowired
    UserService userService;

    @Transactional
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
        if (roles.contains("ADMIN")) {
            response.sendRedirect("/admin");
        } else {
            if (roles.contains("SPONSOR")) {
                response.sendRedirect("/sponsor");
            } else {


               // ModelAndView redPage = new ModelAndView("redirect:profile");

                UserDetails ud = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal() ;
                User user = userService.findByLogin(ud.getUsername());

                request.setAttribute("user", user);

              //  redPage.addObject("user",user);
               redirectStrategy.sendRedirect(request,response,"/profile");

            }
        }
    }

}
