package com.delicious.service;

import com.delicious.jpa.UserRepository;
import com.delicious.model.RichUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.net.URL;
import java.util.Collection;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class UserService {

    private static final Logger LOGGER = Logger.getLogger(UserService.class.getName());

    @Autowired
    private UserRepository userRepository;

    public RichUser createUser(String name, String lastname, URL image, String email, Collection<? extends GrantedAuthority> authorities){
        RichUser response;
        RichUser user = new RichUser(name, lastname, image, email, authorities);
        response = this.userRepository.save(user);
        LOGGER.info("New user: " + user.toString());
        return response;
    }

    public Boolean contains(String email) {
        Optional<RichUser> user = this.userRepository.findByEmail(email);
        return user.isPresent();
    }

}
