package com.delicious.config;

import com.delicious.component.AuthenticationFailed;
import com.delicious.component.AuthenticationSuccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    AuthenticationSuccess authenticationSuccess;

    @Autowired
    AuthenticationFailed authenticationFailed;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .csrf().disable() // TODO ! should be enabled when we use the API from a browser, and disabled when we want use it from postman
            .authorizeRequests().antMatchers("/", "/custom_login", "/h2-console/**").permitAll()
            .anyRequest().authenticated()
            .and()
            .oauth2Login()
            .successHandler(authenticationSuccess)
            .failureHandler(authenticationFailed)
            .and().authorizeRequests()
//            .antMatchers(HttpMethod.GET).permitAll() // TODO when roles were defined, we should change POST and PUT configuration
////          .antMatchers(HttpMethod.POST, "/recipe/**").hasRole("ADMIN")
//            .antMatchers(HttpMethod.POST).permitAll()
////          .antMatchers(HttpMethod.PUT, "/recipe/**").hasRole("ROLE_USER")
//            .antMatchers(HttpMethod.PUT).permitAll()
            ;
    }

}