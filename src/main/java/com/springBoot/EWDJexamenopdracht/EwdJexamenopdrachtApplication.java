package com.springBoot.EWDJexamenopdracht;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class EwdJexamenopdrachtApplication implements WebMvcConfigurer {

	public static void main(String[] args) {
		SpringApplication.run(EwdJexamenopdrachtApplication.class, args);
	}
	
	
	@Override
    public void addViewControllers(org.springframework.web.servlet.config.annotation.ViewControllerRegistry registry) {
        registry.addRedirectViewController("/", "/login");
    }

}
