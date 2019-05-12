package com.delicious;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
//@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/", "/custom_login").permitAll()
                .anyRequest().authenticated()
                .and()
                .oauth2Login()
                .defaultSuccessUrl("/loginSuccess")
                .failureUrl("/loginFailure");
    }

//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests().antMatchers("/login","/").permitAll().anyRequest().authenticated();
//    }

//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
////        http.authorizeRequests()
//////                .antMatchers("/").permitAll()  excluded urls from spring security
////                .anyRequest().authenticated()
////                .and()
////                .formLogin()
////                .loginPage("/login")
////                .permitAll()
////                .and()
////                .logout()
////                .permitAll();
//        http.csrf().disable().
//                authorizeRequests()
//                .antMatchers("/","/login", "/custom_login")
////                .antMatchers("/oauth_login", "/login") //  TODO .antMatchers("/", "/home")
//                .permitAll()
//                .anyRequest()
//                .authenticated();
////                .and()
//////                .oauth2Login()
////                .formLogin()
////                .loginPage("/login")
////                .permitAll()
////                .and()
////                .logout().invalidateHttpSession(true).clearAuthentication(true)
////                .logoutRequestMatcher(new AntPathRequestMatcher("/loginFailure"))
//////                .defaultSuccessUrl("/loginSuccess")
//////                .failureUrl("/loginFailure")
//////                .and()
////                .logoutSuccessUrl("/loginSuccess").permitAll()
////                ;
//
//
////              .clientRegistrationRepository(clientRegistrationRepository())
////                .authorizedClientService(authorizedClientService());
//    }

//    /**
//     * sets up an in-memory user store with a single user
//     * TODO > we should use db users
//     */
//    @Bean
//    @Override
//    public UserDetailsService userDetailsService() {
//        UserDetails user =
//                User.withDefaultPasswordEncoder()
//                        .username("user")
//                        .password("password")
//                        .roles("USER")
//                        .build();
//
//        return new InMemoryUserDetailsManager(user);
//    }


}