package com.delicious.service;

import com.delicious.jpa.UserRepository;
import com.delicious.model.RichUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.net.URL;
import java.util.Collection;
import java.util.Optional;

@Slf4j
@Service
public class AuthenticationService {

    @Autowired
    private UserRepository userRepository;

    public RichUser getUserByEmail(String email) {
        Optional<RichUser> user = this.userRepository.findByEmail(email);
        return user.isPresent() ? user.get() : null;
    }

    @Transactional
    public RichUser createUser(String name, String lastname, URL image, String email, Collection<? extends GrantedAuthority> authorities){
        RichUser response;
        RichUser user = RichUser.builder().name(name).lastName(lastname).image(image).email(email).build();
        response = this.userRepository.save(user);
        return response;
    }

    public Boolean contains(String email) {
        Optional<RichUser> user = this.userRepository.findByEmail(email);
        return user.isPresent();
    }

    @Transactional
    public RichUser updateUser(String email, RichUser userChanges) {

        RichUser result = null;
        RichUser user = getUserByEmail(email);

        if(user != null) {
            if (userChanges.getName() != null)
                user.setName(userChanges.getName());
            if (userChanges.getLastName() != null)
                user.setLastName(userChanges.getLastName());
//            if (userChanges.getEmail() != null) // no updetear el email porque sino se pierde la unicidad y otras partes de la aplicacion pueden empezar a andar mal
//                user.setEmail(userChanges.getEmail());
            if (userChanges.getImage() != null)
                user.setImage(userChanges.getImage());
            result = userRepository.save(user);
        }

        return result;
    }


}
