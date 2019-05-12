package com.delicious.service;

import com.delicious.jpa.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

//    public Recipe getRandomRecipe() {
//        Long numberOfRecipes = recipeRepository.count();
//        Random random = new Random();
//        Long aleatoryRecipePageId = random.longs(0, numberOfRecipes).findFirst().getAsLong();
//        Page<Recipe> recipePage = recipeRepository.findAll(PageRequest.of(aleatoryRecipePageId.intValue(), 1));
//        return recipePage.hasContent() ? recipePage.getContent().get(0) : null;
//    }

//    public Recipe creteRecipe(String name, String description, byte[] image, URL link) {
//        Recipe recipe = new Recipe(name, description, image, link);
//        return recipeRepository.save(recipe);
//    }
}
