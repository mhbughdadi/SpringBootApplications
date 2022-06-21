package com.apogee.securitydemo.security;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{




//    @Bean
//    @ConfigurationProperties(prefix = "spring.datasource")
//    public DataSource dataSource(){
//        DataSourceTransactionManager manager = new DataSourceTransactionManager();
//        return manager.getDataSource();
//    }


    @Autowired
    private UserDetailsService userDetailService;
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

//        auth.jdbcAuthentication()
//                .dataSource(dataSource);


        auth.userDetailsService(userDetailService);
    }

    @Bean
    PasswordEncoder getPasswordEncoder(){
        return  NoOpPasswordEncoder.getInstance();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
            .antMatchers("/admin").hasAnyAuthority("adm")
            .antMatchers("/user").hasAnyAuthority("cus")
            .antMatchers("/").permitAll()
            .and().formLogin();
    }
    
}
