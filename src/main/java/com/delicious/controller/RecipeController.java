package com.delicious.controller;

import com.delicious.model.Recipe;
import com.delicious.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
public class RecipeController {

    @Autowired
    private RecipeService recipeService;

    @GetMapping("/random/recipe")
    @ResponseBody
    public ResponseEntity getRandomRecipe() {

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

}