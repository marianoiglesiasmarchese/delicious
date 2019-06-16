package com.delicious.service;

import com.delicious.exception.UserHasNotThisRecipeException;
import com.delicious.jpa.RecipeRepository;
import com.delicious.model.Recipe;
import com.delicious.model.RichUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class RecipeService {

    @Autowired
    RecipeRepository recipeRepository;

    @Transactional
    public Recipe creteRecipe(Recipe recipe) {
        return recipeRepository.save(recipe);
    }

    public Recipe getRecipe(Long id){
        Optional<Recipe> optionalRecipe = recipeRepository.getById(id);
        return optionalRecipe.isPresent() ? optionalRecipe.get() : null;
    }

    @Transactional
    public Recipe updateRecipe(Long id, Recipe recipeChanges) {

        Recipe result = null;
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
        }

        return result;
    }

    @Transactional
    public Recipe increaseScore(Long id, Integer stars, RichUser user){

        Recipe recipeResponse = null;

        final Recipe incomingRecipe = getRecipe(id);

        Optional<Recipe> optionalRecipe = user.getRecipes().stream().filter(userRecipe -> incomingRecipe.equals(userRecipe)).findFirst();
        if(optionalRecipe.isPresent()){
            recipeResponse = optionalRecipe.get().increaseStars(stars, user);
            recipeResponse = recipeRepository.save(recipeResponse);
        }else{
            throw new UserHasNotThisRecipeException();
        }

        return recipeResponse;
    }

    @Transactional
    public Recipe decreaseScore(Long id, RichUser user){

        Recipe recipeResponse = null;

        final Recipe incomingRecipe = getRecipe(id);

        Optional<Recipe> optionalRecipe = user.getRecipes().stream().filter(userRecipe -> incomingRecipe.equals(userRecipe)).findFirst();
        if(optionalRecipe.isPresent()){
            recipeResponse = optionalRecipe.get().decreaseStars(user);
            recipeResponse = recipeRepository.save(recipeResponse);
        }else{
            throw new UserHasNotThisRecipeException();
        }

        return recipeResponse;
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

}
