package com.delicious.component;

import com.delicious.model.RichUser;
import com.delicious.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class CommonComponent implements AuthenticationFacade {

    @Autowired
    private AuthenticationService authenticationService;

    @Override
    public Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    protected RichUser getCurrentUser(){
        DefaultOidcUser principal = (DefaultOidcUser) getAuthentication().getPrincipal();
        Map<String, Object> attributes = principal.getAttributes();
        Object email = attributes.get("email");
        return authenticationService.getUserByEmail((String) email);
    }

    protected RichUser updateUser(RichUser userChanges){
        return authenticationService.updateUser(getCurrentUser().getEmail(), userChanges);
    }

}
