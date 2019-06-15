package com.delicious.service;

import com.delicious.jpa.UserRepository;
import com.delicious.model.Recipe;
import com.delicious.model.RichUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public RichUser addRecipe(Recipe recipe, RichUser user) {
        Boolean isPresent = user.getRecipes().contains(recipe);
        if(!isPresent){
            user.addRecipe(recipe);
            user = userRepository.save(user);
        }
        return user;
    }

    @Transactional
    public RichUser removeRecipe(Recipe recipe, RichUser user) {
        Boolean isPresent = user.getRecipes().contains(recipe);
        if(isPresent){
            user.removeRecipe(recipe);
            user = userRepository.save(user);
        }
        return user;
    }
}
