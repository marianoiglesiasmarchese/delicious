package com.delicious.controller;

import com.delicious.component.CommonComponent;
import com.delicious.model.Recipe;
import com.delicious.model.RichUser;
import com.delicious.service.RecipeService;
import com.delicious.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Slf4j
@RestController
public class UserController extends CommonComponent {

    @Autowired
    private UserService userService;

    @Autowired
    private RecipeService recipeService;

    @GetMapping(value = "/user/recipes")
    @ResponseBody
    public ResponseEntity recipes() {

        Map<String, Object> response = new HashMap<>();
        HttpStatus status = HttpStatus.OK;

        Set<Recipe> recipes = getCurrentUser().getRecipes();
        response.put("recipes", recipes);

        return new ResponseEntity(response, status);

    }

    @PutMapping(value = "/user/recipe/{id}/add")
    @ResponseBody
    public ResponseEntity addRecipe(
            @PathVariable Long id
    ) {

        Map<String, Object> response = new HashMap<>();
        HttpStatus status = HttpStatus.OK;


        Recipe recipe = recipeService.getRecipe(id);
        RichUser user = userService.addRecipe(recipe, getCurrentUser());
        response.put("user", user);

        return new ResponseEntity(response, status);

    }

    @PutMapping(value = "/user/recipe/{id}/remove")
    @ResponseBody
    public ResponseEntity removeRecipe(
            @PathVariable Long id
    ) {

        Map<String, Object> response = new HashMap<>();
        HttpStatus status = HttpStatus.OK;


        Recipe recipe = recipeService.getRecipe(id);
        RichUser user = userService.removeRecipe(recipe, getCurrentUser());
        response.put("user", user);


        return new ResponseEntity(response, status);

    }

    @GetMapping(value = "/user/profile")
    @ResponseBody
    public ResponseEntity profile() {

        Map<String, Object> response = new HashMap<>();
        HttpStatus status = HttpStatus.OK;


        RichUser richUser = getCurrentUser();
        response.put("user", richUser);


        return new ResponseEntity(response, status);

    }

    @PutMapping(value = "/user/profile/update")
    @ResponseBody
    public ResponseEntity editProfile(
            @RequestBody RichUser userChanges
    ) {

        Map<String, Object> response = new HashMap<>();
        HttpStatus status = HttpStatus.OK;


        RichUser richUser = updateUser(userChanges);
        response.put("user", richUser);


        return new ResponseEntity(response, status);

    }


}