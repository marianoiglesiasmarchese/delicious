package com.delicious.controller;

import com.delicious.component.CommonComponent;
import com.delicious.model.Recipe;
import com.delicious.model.RichUser;
import com.delicious.service.RecipeService;
import com.delicious.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.logging.Logger;

@RestController
public class UserController extends CommonComponent {

    private static final Logger LOGGER = Logger.getLogger(UserController.class.getName());

    @Autowired
    private UserService userService;

    @Autowired
    private RecipeService recipeService;

    @GetMapping(value = "/user/recipes")
    @ResponseBody
    public ResponseEntity recipes() {

        Map<String,Object> response = new HashMap<>();
        HttpStatus status = HttpStatus.OK;

        try{
            Set<Recipe> recipes = getCurrentUser().getRecipes();
            response.put("recipes",recipes);
        }
        catch(Exception e){
            response.put("error", e.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity(response, status);

    }

    @PutMapping(value = "/user/recipe/{id}/add")
    @ResponseBody
    public ResponseEntity addRecipe(
            @PathVariable Long id
    ) {

        Map<String,Object> response = new HashMap<>();
        HttpStatus status = HttpStatus.OK;

        try{
            Recipe recipe = recipeService.getRecipe(id);
            userService.addRecipe(recipe, getCurrentUser());
        }
        catch(Exception e){
            response.put("error", e.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity(response, status);

    }

    @PutMapping(value = "/user/recipe/{id}/remove")
    @ResponseBody
    public ResponseEntity removeRecipe(
            @PathVariable Long id
    ) {

        Map<String,Object> response = new HashMap<>();
        HttpStatus status = HttpStatus.OK;

        try{
            Recipe recipe = recipeService.getRecipe(id);
            userService.removeRecipe(recipe, getCurrentUser());
        }
        catch(Exception e){
            response.put("error", e.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity(response, status);

    }

    @GetMapping(value = "/user/profile")
    @ResponseBody
    public ResponseEntity profile() {

        Map<String,Object> response = new HashMap<>();
        HttpStatus status = HttpStatus.OK;

        try{
            RichUser richUser = getCurrentUser();
            response.put("user", richUser);
        }
        catch(Exception e){
            response.put("error", e.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity(response, status);

    }

    @PostMapping(value = "/user/profile/update")
    @ResponseBody
    public ResponseEntity editProfile(
            @RequestBody RichUser userChanges
    ) {

        Map<String,Object> response = new HashMap<>();
        HttpStatus status = HttpStatus.OK;

        try{
            RichUser richUser = updateUser(userChanges);
            response.put("user", richUser);
        }
        catch(Exception e){
            response.put("error", e.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity(response, status);

    }



}