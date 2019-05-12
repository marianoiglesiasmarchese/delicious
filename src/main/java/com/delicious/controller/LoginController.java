package com.delicious.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LoginController {

    @RequestMapping("/internal_login")
    @ResponseBody
    public String login(String name, String password) {

        return "{ name: "+name+", password: " + password +"}";
    }


}
