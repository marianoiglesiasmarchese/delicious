package com.delicious.component;

import com.delicious.model.RichUser;
import com.delicious.service.AuthenticationService;
import lombok.extern.slf4j.Slf4j;
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

@Slf4j
@Component
public class AuthenticationSuccess implements AuthenticationSuccessHandler {

    private static final String BASE_MESSAGE = "OAuth2 - Authentication successful";

    @Autowired
    private AuthenticationService authenticationService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());

        log.info(BASE_MESSAGE);

        this.checkIfAlreadyExist(authentication);

        // TODO here we should redirect to specific view for each user
        if (roles.contains("ROLE_ADMIN")) {
            response.sendRedirect("/new");
        } else{
            response.sendRedirect("/user/profile");
        }

        log.debug(BASE_MESSAGE + " - " + roles + " signed in...");

    }

    private void checkIfAlreadyExist(Authentication authentication) throws MalformedURLException {

        DefaultOidcUser principal = (DefaultOidcUser) authentication.getPrincipal();
        Map<String, Object> attributes = principal.getAttributes();
        Object locale = attributes.get("locale");
        Object picture = attributes.get("picture");
        Object name = attributes.get("name");
        Object email = attributes.get("email");
        Object email_verified = attributes.get("email_verified");

        Boolean alreadyRegistered = authenticationService.contains((String) email);
        if(!alreadyRegistered){
            log.info(BASE_MESSAGE + " - new user, signing up...");
            String lastname = "";
            URL pictureURL = new URL((String) picture);
            RichUser richUser = authenticationService.createUser((String) name, lastname, pictureURL, (String) email, authentication.getAuthorities());
            log.debug(BASE_MESSAGE + " - user created: " + richUser);
        }
    }

}
