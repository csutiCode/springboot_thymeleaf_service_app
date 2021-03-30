package com.example.wifidemo.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


import javax.sql.DataSource;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/**").permitAll()
                .antMatchers("/").permitAll()
                .antMatchers("/showNewQualificationForm/*").permitAll()
                .antMatchers("/info/*").permitAll()
                .antMatchers("/showNewQualificationForm/*").permitAll()
                .anyRequest()
                .authenticated()
                .and().formLogin().defaultSuccessUrl("/forLoggedInUser")
                .and().logout().permitAll().logoutSuccessUrl("/welcome");


    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }



}
