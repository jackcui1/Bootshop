package com.bootshop.config;

import com.bootshop.model.User;
import com.bootshop.service.UserRedisService;
import com.bootshop.service.UserService;
import org.apache.commons.logging.LogFactory;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.logging.Logger;

/**
 * after user login successfully, redirect to different page.
 * and record user information and so on.
 *
 * @author Guowei Cui
 * @date 8/17/2018 12:46 PM
 */
@Component
public class SimpleAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    //Logger logger = (Logger) LoggerFactory.getLogger(SimpleAuthenticationSuccessHandler.class);

    @Autowired
    private UserRedisService userRedisservice;

    @Autowired
    private UserService userService;

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        Collection<GrantedAuthority> authorities = (Collection<GrantedAuthority>) authentication.getAuthorities();
        saveLoginUserInfo(request, authentication);
        authorities.forEach(authority -> {
            if (authority.getAuthority().equals("ROLE_USER")) {
                try {
                    redirectStrategy.sendRedirect(request, response, "/");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (authority.getAuthority().equals("ROLE_ADMIN")) {
                try {
                    redirectStrategy.sendRedirect(request, response, "/admin");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                throw new IllegalStateException();
            }
        });

    }

    public void saveLoginUserInfo(HttpServletRequest request, Authentication authentication) {

        String username = authentication.getName();
        WebAuthenticationDetails details = (WebAuthenticationDetails) SecurityContextHolder.getContext().getAuthentication().getDetails();
        User user = userService.findByUsername(username);
        if (user != null) {
            String ip = details.getRemoteAddress();
            user.setCreateAt(Timestamp.valueOf(LocalDateTime.now()));
            user.setLoggedInAt(Timestamp.valueOf(LocalDateTime.now()));
            user.setLoginIp(ip);
            this.userService.save(user);
            //Record to redis database.
            this.userRedisservice.add(user);
        }
    }

}
