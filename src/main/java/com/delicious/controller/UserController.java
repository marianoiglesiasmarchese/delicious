package com.delicious.controller;

import com.delicious.component.CommonComponent;
import com.delicious.model.Recipe;
import com.delicious.model.RichUser;
import com.delicious.service.RecipeService;
import com.delicious.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<Set<Recipe>> recipes() {

        Set<Recipe> recipes = getCurrentUser().getRecipes();

        return ResponseEntity.ok().body(recipes);
    }

    @PutMapping(value = "/user/recipe/{id}/add")
    @ResponseBody
    public ResponseEntity<RichUser> addRecipe(@PathVariable Long id) {

        Recipe recipe = recipeService.getRecipe(id);
        RichUser user = userService.addRecipe(recipe, getCurrentUser());

        return  ResponseEntity.ok().body(user);
    }

    @PutMapping(value = "/user/recipe/{id}/remove")
    @ResponseBody
    public ResponseEntity<RichUser> removeRecipe(@PathVariable Long id) {

        Recipe recipe = recipeService.getRecipe(id);
        RichUser user = userService.removeRecipe(recipe, getCurrentUser());

        return ResponseEntity.ok().body(user);
    }

    @GetMapping(value = "/user/profile")
    @ResponseBody
    public ResponseEntity<RichUser> profile() {

        RichUser richUser = getCurrentUser();

        return ResponseEntity.ok().body(richUser);
    }

    @PutMapping(value = "/user/profile/update")
    @ResponseBody
    public ResponseEntity<RichUser> editProfile(@RequestBody RichUser userChanges) {

        RichUser richUser = updateUser(userChanges);

        return ResponseEntity.ok().body(richUser);
    }

}