package com.delicious.service;

import com.delicious.jpa.RecipeRepository;
import com.delicious.model.Recipe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.net.URL;
import java.util.List;
import java.util.Random;

@Service
public class RecipeService {

    @Autowired
    RecipeRepository recipeRepository;

    public Recipe getRandomRecipe() {
        Long numberOfRecipes = recipeRepository.count();
        Random random = new Random();
        Long aleatoryRecipePageId = random.longs(0, numberOfRecipes).findFirst().getAsLong();
        Page<Recipe> recipePage = recipeRepository.findAll(PageRequest.of(aleatoryRecipePageId.intValue(), 1));
        return recipePage.hasContent() ? recipePage.getContent().get(0) : null;
    }

    public Recipe creteRecipe(String name, String description, byte[] image, URL link) {
        Recipe recipe = new Recipe(name, description, image, link);
        return recipeRepository.save(recipe);
    }
}
