package com.delicious.controller;

import com.delicious.component.CommonComponent;
import com.delicious.dto.Star;
import com.delicious.model.Recipe;
import com.delicious.service.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class RecipeController extends CommonComponent {

    @Autowired
    private RecipeService recipeService;

    @PostMapping(value = "/recipe/new")
    @ResponseBody
    public ResponseEntity<Recipe> createRecipe(@RequestBody Recipe newRecipe) {

        Recipe recipe = recipeService.creteRecipe(newRecipe);

        log.debug("Recipe created: " + recipe.toString());

        return ResponseEntity.ok().body(recipe);
    }

    @PutMapping(value = "/recipe/{id}/update")
    @ResponseBody
    public ResponseEntity<Recipe> updateRecipe(@RequestBody Recipe recipeChanges, @PathVariable Long id) {

        Recipe recipe = recipeService.updateRecipe(id, recipeChanges);

        log.debug("Recipe updated: " + recipe.toString());

        return ResponseEntity.ok().body(recipe);
    }

    @PutMapping(value = "/recipe/{id}/score/increase")
    @ResponseBody
    public ResponseEntity<Recipe> updateRecipe(@RequestBody @Validated Star stars, @PathVariable Long id) {

        Recipe recipe = recipeService.increaseScore(id, stars.getStars(), getCurrentUser());

        log.debug("Recipe: " + recipe.toString() + " - increase score to: " + recipe.getAvgStarts());

        return ResponseEntity.ok().body(recipe);
    }

    @PutMapping(value = "/recipe/{id}/score/decrease")
    @ResponseBody
    public ResponseEntity<Recipe> updateRecipe(@PathVariable Long id) {

        Recipe recipe = recipeService.decreaseScore(id, getCurrentUser());

        log.debug("Recipe: " + recipe.toString() + " - decrease score to: " + recipe.getAvgStarts());

        return ResponseEntity.ok().body(recipe);
    }

    @GetMapping(value = "/recipe/random")
    @ResponseBody
    public ResponseEntity<Recipe> randomRecipe() {

        Recipe recipe = recipeService.getRandomRecipe();

        log.debug("Random recipe: " + recipe.toString());

        return ResponseEntity.ok().body(recipe);
    }

    @GetMapping(value = "/recipe/random/set")
    @ResponseBody
    public ResponseEntity<List<Recipe>> setOfRandomRecipes(@RequestParam(name = "size", required = true) Integer size) {

        List<Recipe> recipes = recipeService.getSetOfRandomRecipes(size);

        log.debug("Random recipes: " + recipes.toString());

        return ResponseEntity.ok().body(recipes);
    }

}