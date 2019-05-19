package com.delicious.service;

import com.delicious.jpa.UserRepository;
import com.delicious.model.Recipe;
import com.delicious.model.RichUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.net.URL;
import java.util.Collection;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class AuthenticationService {

    private static final Logger LOGGER = Logger.getLogger(AuthenticationService.class.getName());

    @Autowired
    private UserRepository userRepository;

    public RichUser getUserByEmail(String email) {
        Optional<RichUser> user = this.userRepository.findByEmail(email);
        return user.isPresent() ? user.get() : null;
    }

    public RichUser createUser(String name, String lastname, URL image, String email, Collection<? extends GrantedAuthority> authorities){
        RichUser response;
        RichUser user = new RichUser(name, lastname, image, email, authorities);
        response = this.userRepository.save(user);
        return response;
    }

    public Boolean contains(String email) {
        Optional<RichUser> user = this.userRepository.findByEmail(email);
        return user.isPresent();
    }

    public RichUser updateUser(String email, RichUser userChanges) {

        RichUser result;
        RichUser user = getUserByEmail(email);

        if(user != null) {
            if (userChanges.getName() != null)
                user.setName(userChanges.getName());
            if (userChanges.getEmail() != null)
                user.setEmail(userChanges.getEmail());
            result = userRepository.save(user);
        }else{
            result = userRepository.save(userChanges);
        }

        return result;
    }


}
