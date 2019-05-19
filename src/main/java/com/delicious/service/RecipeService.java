package com.delicious.service;

import com.delicious.jpa.RecipeRepository;
import com.delicious.model.Recipe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.logging.Logger;

@Service
public class RecipeService {

    private static final Logger LOGGER = Logger.getLogger(RecipeService.class.getName());

    @Autowired
    RecipeRepository recipeRepository;

    public Recipe creteRecipe(Recipe recipe) {
        return recipeRepository.save(recipe);
    }

    public Recipe updateRecipe(Long id, Recipe recipeChanges) {

        Recipe result;
        Recipe recipe = getRecipe(id);

        if(recipe != null) {
            if (recipeChanges.getName() != null)
                recipe.setName(recipeChanges.getName());
            if (recipeChanges.getDescription() != null)
                recipe.setDescription(recipeChanges.getDescription());
            if (recipeChanges.getImage() != null)
                recipe.setImage(recipeChanges.getImage());
            if (recipeChanges.getLink() != null)
                recipe.setLink(recipeChanges.getLink());
            result = recipeRepository.save(recipe);
        }else{
            result = recipeRepository.save(recipeChanges);
        }

        return result;
    }

    public Recipe getRandomRecipe() {
        Long numberOfRecipes = recipeRepository.count();
        Random random = new Random();
        Long aleatoryRecipePageId = random.longs(0, numberOfRecipes).findFirst().getAsLong();
        Page<Recipe> recipePage = recipeRepository.findAll(PageRequest.of(aleatoryRecipePageId.intValue(), 1));
        return recipePage.hasContent() ? recipePage.getContent().get(0) : null;
    }

    public List<Recipe> getSetOfRandomRecipes(Integer size) {
        Long numberOfRecipes = recipeRepository.count();
        Long numberOfPages = numberOfRecipes / size;
        Random random = new Random();
        Long aleatoryRecipePageId = random.longs(0, numberOfPages).findFirst().getAsLong();
        Page<Recipe> recipePage = recipeRepository.findAll(PageRequest.of(aleatoryRecipePageId.intValue(), size));
        return recipePage.hasContent() ? recipePage.getContent() : null;
    }

    public Recipe getRecipe(Long id){
        Optional<Recipe> optionalRecipe = recipeRepository.getById(id);
        return optionalRecipe.isPresent() ? optionalRecipe.get() : null;
    }
}
