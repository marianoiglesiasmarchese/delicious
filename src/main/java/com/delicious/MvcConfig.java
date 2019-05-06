package com.delicious;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("new");
        registry.addViewController("/login").setViewName("login");
        // TODO > this enpoints are not recognized automatically on this map, maybe the controller where they are defined is not recognized as MVC controller, but this is only temporal so it doesn't matter
        registry.addViewController("/recipe/new").setViewName("new");
        registry.addViewController("/recipe/random").setViewName("random_recipe");
    }

}