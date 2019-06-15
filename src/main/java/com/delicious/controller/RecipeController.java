package com.delicious.controller;

import com.delicious.component.CommonComponent;
import com.delicious.dto.Star;
import com.delicious.model.Recipe;
import com.delicious.service.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

@Slf4j
@RestController
public class RecipeController extends CommonComponent {

    @Autowired
    private RecipeService recipeService;

    @PostMapping(value = "/recipe/new")
    @ResponseBody
    public ResponseEntity createRecipe(@RequestBody Recipe newRecipe) {

        Map<String, Object> response = new HashMap<>();
        HttpStatus status = HttpStatus.OK;

        Recipe recipe = recipeService.creteRecipe(newRecipe);
        response.put("recipe", recipe);

        return new ResponseEntity(response, status);
    }

    @PutMapping(value = "/recipe/{id}/update")
    @ResponseBody
    public ResponseEntity updateRecipe(
            @RequestBody Recipe recipeChanges,
            @PathVariable Long id
    ) {

        Map<String, Object> response = new HashMap<>();
        HttpStatus status = HttpStatus.OK;


        Recipe recipe = recipeService.updateRecipe(id, recipeChanges);
        response.put("recipe", recipe);


        return new ResponseEntity(response, status);
    }

    @PutMapping(value = "/recipe/{id}/score/increase")
    @ResponseBody
    public ResponseEntity updateRecipe(
            @RequestBody @Validated Star stars,
            @PathVariable Long id
    ) {

        Map<String, Object> response = new HashMap<>();
        HttpStatus status = HttpStatus.OK;


        Recipe recipe = recipeService.increaceScore(id, stars.getStars(), getCurrentUser());
        response.put("recipe", recipe);


        return new ResponseEntity(response, status);
    }

    @PutMapping(value = "/recipe/{id}/score/decrease")
    @ResponseBody
    public ResponseEntity updateRecipe(
            @PathVariable Long id
    ) {

        Map<String, Object> response = new HashMap<>();
        HttpStatus status = HttpStatus.OK;


        Recipe recipe = recipeService.decreaceScore(id, getCurrentUser());
        response.put("recipe", recipe);

        return new ResponseEntity(response, status);
    }

    @GetMapping(value = "/recipe/random")
    @ResponseBody
    public ResponseEntity randomRecipe() {

        Map<String, Object> response = new HashMap<>();
        HttpStatus status = HttpStatus.OK;


        Recipe recipe = recipeService.getRandomRecipe();
        response.put("recipe", recipe);


        return new ResponseEntity(response, status);
    }

    @GetMapping(value = "/recipe/random/set")
    @ResponseBody
    public ResponseEntity setOfRandomRecipes(
            @RequestParam(name = "size", required = true) Integer size
    ) {

        Map<String, Object> response = new HashMap<>();
        HttpStatus status = HttpStatus.OK;

        List<Recipe> recipes = recipeService.getSetOfRandomRecipes(size);
        response.put("recipes", recipes);

        return new ResponseEntity(response, status);
    }

}