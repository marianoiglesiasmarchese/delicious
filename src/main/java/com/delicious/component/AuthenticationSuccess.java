package com.delicious.component;

import com.delicious.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

@Component
public class AuthenticationSuccess implements AuthenticationSuccessHandler {

    private static final Logger LOGGER = Logger.getLogger(AuthenticationSuccess.class.getName());

    @Autowired
    private UserService userService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());

        this.checkIfAlreadyExist(authentication);

        if (roles.contains("ROLE_ADMIN")) {
            response.sendRedirect("/new");
        } else{
            response.sendRedirect("/home");
        }

    }

    private void checkIfAlreadyExist(Authentication authentication) throws MalformedURLException {

        DefaultOidcUser principal = (DefaultOidcUser) authentication.getPrincipal();
        Map<String, Object> attributes = principal.getAttributes();
        Object locale = attributes.get("locale");
        Object picture = attributes.get("picture");
        Object name = attributes.get("name");
        Object email = attributes.get("email");
        Object email_verified = attributes.get("email_verified");

        System.out.println(principal);

        Boolean alreadyRegistered = this.userService.contains((String) email);
        if(!alreadyRegistered){
            String lastname = "";
            URL pictureURL = new URL((String) picture);
            this.userService.createUser((String) name, lastname, pictureURL, (String) email, authentication.getAuthorities());
        }
    }

}
