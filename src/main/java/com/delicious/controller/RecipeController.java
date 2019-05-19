package com.delicious.controller;

import com.delicious.model.Recipe;
import com.delicious.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

@Controller
public class RecipeController {

    private static final Logger LOGGER = Logger.getLogger(RecipeController.class.getName());

    @Autowired
    private RecipeService recipeService;

    @RequestMapping(value = "/recipe/random", method = RequestMethod.GET)
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

    @RequestMapping(value = "/recipe/random/set", method = RequestMethod.GET)
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

    @RequestMapping(value = "/recipe/new", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity createRecipe(
            @RequestParam(name="name", required=true) String name,
            @RequestParam(name="description", required=true) String description,
            @RequestParam(name="image", required=true) byte[] image,
            @RequestParam(name="link", required=false) URL link
    ) {

        Map<String,Object> response = new HashMap<>();
        HttpStatus status = HttpStatus.OK;

        try{
            Recipe recipe = recipeService.creteRecipe(name, description, image, link);
            response.put("recipe", recipe);
        }
        catch(Exception e){
            response.put("error", e.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity(response, status);
    }

    // TODO puntua una receta y almacena quien la puntuó.
    @RequestMapping(value = "/recipe/score", method = RequestMethod.PUT)
    @ResponseBody
    public Object editRecipe(Long starts, String recipe) {
        return this;
    }

}