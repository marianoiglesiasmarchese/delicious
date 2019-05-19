package com.delicious.service;

import com.delicious.jpa.UserRepository;
import com.delicious.model.Recipe;
import com.delicious.model.RichUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class UserService {

    private static final Logger LOGGER = Logger.getLogger(UserService.class.getName());

    @Autowired
    private UserRepository userRepository;

    public RichUser addRecipe(Recipe recipe, RichUser user) {
        Boolean isPresent = user.getRecipes().contains(recipe);
        if(!isPresent){
            user.addRecipe(recipe);
        }
        return userRepository.save(user);
    }

    public RichUser removeRecipe(Recipe recipe, RichUser user) {
        Boolean isPresent = user.getRecipes().contains(recipe);
        if(isPresent){
            user.removeRecipe(recipe);
        }
        return userRepository.save(user);
    }
}
