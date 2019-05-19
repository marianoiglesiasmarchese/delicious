package com.delicious.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;

@RestController
public class LoginController {

    private static final Logger LOGGER = Logger.getLogger(LoginController.class.getName());

    @RequestMapping("/internal_login")
    @ResponseBody
    public String login(String name, String password) {
        return "{ name: " + name + ", password: " + password + "}";
    }


}
