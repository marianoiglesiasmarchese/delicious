package com.delicious.controller;

import com.delicious.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;
import java.util.logging.Logger;

@Controller
public class UserController {

    private static final Logger LOGGER = Logger.getLogger(UserController.class.getName());

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    @ResponseBody
    public Principal getUser(Principal principal) {
        return principal;
    }

    // TODO retorna todas las recetas de una persona
    @RequestMapping("/user/recipes")
    @ResponseBody
    public Object getRecipes() {
        return this;
    }

    // TODO agrega una receta a la lista de recetas del usuario
    @RequestMapping("/user/recipe")
    @ResponseBody
    public Object addRecipe(String recipe) {
        return this;
    }

    // TODO quita una receta a la lista de recetas del usuario
    @RequestMapping("/user/recipe/remove")
    @ResponseBody
    public Object removeRecipe(String recipe) {
        return this;
    }

    // TODO obtiene el perfil
    @RequestMapping(value = "/user/profile", method = RequestMethod.GET)
    @ResponseBody
    public Object getProfile(String nombre) {
        return this;
    }

    // TODO actualiza el perfil
    @RequestMapping(value = "/user/profile", method = RequestMethod.PUT)
    @ResponseBody
    public Object editProfile(String nombre) {
        return this;
    }



}