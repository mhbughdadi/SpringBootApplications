package com.apogee.securitydemo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // set Configuration to Authintiation Manager Builder

        auth.inMemoryAuthentication()
        .withUser("moh")
        .password("moh")
        .roles("cus")
        .and()
        .withUser("admin")
        .password("admin")
        .roles("adm");
    }

    @Bean
    PasswordEncoder getPasswordEncoder(){
        return  NoOpPasswordEncoder.getInstance();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
            .antMatchers("/user/**").hasAnyRole("cus","adm")
            .antMatchers("/admin/**").hasRole("adm")
            .antMatchers("/").permitAll()
            .and().formLogin();
    }
    
}
