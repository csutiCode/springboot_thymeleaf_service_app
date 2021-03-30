package com.example.wifidemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.example.wifidemo.repository")
@EntityScan("com.example.wifidemo.model")
@ComponentScans({ @ComponentScan("com.example.wifidemo.controller"), @ComponentScan("com.example.wifidemo.security")})
public class WifiDemoApplication {





	public static void main(String[] args) {
		SpringApplication.run(WifiDemoApplication.class, args);










	}




}
