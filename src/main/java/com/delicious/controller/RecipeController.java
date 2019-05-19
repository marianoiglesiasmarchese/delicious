package com.delicious.controller;

import com.delicious.component.CommonComponent;
import com.delicious.model.Recipe;
import com.delicious.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

@RestController
public class RecipeController extends CommonComponent {

    private static final Logger LOGGER = Logger.getLogger(RecipeController.class.getName());

    @Autowired
    private RecipeService recipeService;

    @PostMapping(value = "/recipe/new")
    @ResponseBody
    public ResponseEntity createRecipe(@RequestBody Recipe newRecipe) {

        Map<String,Object> response = new HashMap<>();
        HttpStatus status = HttpStatus.OK;

        try{
            Recipe recipe = recipeService.creteRecipe(newRecipe);
            response.put("recipe", recipe);
        }
        catch(Exception e){
            response.put("error", e.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity(response, status);
    }

    @PutMapping(value = "/recipe/update/{id}")
    @ResponseBody
    public ResponseEntity updateRecipe(
            @RequestBody Recipe recipeChanges,
            @PathVariable Long id
    ) {

        Map<String,Object> response = new HashMap<>();
        HttpStatus status = HttpStatus.OK;

        try{
            Recipe recipe = recipeService.updateRecipe(id, recipeChanges);
            response.put("recipe", recipe);
        }
        catch(Exception e){
            response.put("error", e.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity(response, status);
    }

    @GetMapping(value = "/recipe/random")
    @ResponseBody
    public ResponseEntity randomRecipe() {

        Map<String,Object> response = new HashMap<>();
        HttpStatus status = HttpStatus.OK;

        try{
            Recipe recipe = recipeService.getRandomRecipe();
            response.put("recipe", recipe);
        }
        catch(Exception e){
            response.put("error", e.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity(response, status);
    }

    @GetMapping(value = "/recipe/random/set")
    @ResponseBody
    public ResponseEntity setOfRandomRecipes(
            @RequestParam(name="size", required=true) Integer size
    ) {

        Map<String,Object> response = new HashMap<>();
        HttpStatus status = HttpStatus.OK;

        try{
            List<Recipe> recipes = recipeService.getSetOfRandomRecipes(size);
            response.put("recipes", recipes);
        }
        catch(Exception e){
            response.put("error", e.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity(response, status);
    }

}