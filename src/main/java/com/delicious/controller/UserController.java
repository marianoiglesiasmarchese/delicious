package com.delicious.controller;

import com.delicious.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/user")
    @ResponseBody
    public Principal user(Principal principal) {

        return principal;
    }

//    @RequestMapping(value = "/user/random", method = RequestMethod.GET)
//    @ResponseBody
//    public ResponseEntity randomRecipe() {
//
//        Map<String,Object> response = new HashMap<>();
//        HttpStatus status = HttpStatus.OK;
//
//        try{
//            Recipe recipe = recipeService.getRandomRecipe();
//            response.put("recipe", recipe);
//        }
//        catch(Exception e){
//            response.put("error", e.getMessage());
//            status = HttpStatus.INTERNAL_SERVER_ERROR;
//        }
//
//        return new ResponseEntity(response, status);
//    }

//    @RequestMapping(value = "/recipe/new", method = RequestMethod.GET)
//    @ResponseBody
//    public ResponseEntity createRecipe(
//            @RequestParam(name="name", required=true) String name,
//            @RequestParam(name="description", required=true) String description,
//            @RequestParam(name="image", required=true) byte[] image,
//            @RequestParam(name="link", required=false) URL link
//    ) {
//
//        Map<String,Object> response = new HashMap<>();
//        HttpStatus status = HttpStatus.OK;
//
//        try{
//            Recipe recipe = recipeService.creteRecipe(name, description, image, link);
//            response.put("recipe", recipe);
//        }
//        catch(Exception e){
//            response.put("error", e.getMessage());
//            status = HttpStatus.INTERNAL_SERVER_ERROR;
//        }
//
//        return new ResponseEntity(response, status);
//    }



}