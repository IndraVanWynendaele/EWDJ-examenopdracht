package com.springBoot.EWDJexamenopdracht;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import service.MyUserDetailsService;
import validator.GameValidator;

@SpringBootApplication
@EnableJpaRepositories("repository")
@EntityScan("domain")
//@ComponentScans({
//	@ComponentScan("service"),
//	@ComponentScan("domain"),
//	@ComponentScan("repository")
//})
public class EwdJexamenopdrachtApplication implements WebMvcConfigurer {

	public static void main(String[] args) {
		SpringApplication.run(EwdJexamenopdrachtApplication.class, args);
	}
	
	
	@Override
    public void addViewControllers(org.springframework.web.servlet.config.annotation.ViewControllerRegistry registry) {
        registry.addRedirectViewController("/", "/sports");
    }
	
	@Bean 
	DateFormatter dateFormatter() {
		return new DateFormatter();
	}
	
	@Bean
	GameValidator registrationValidation() {
		return new GameValidator();
	}
	
	@Bean
	UserDetailsService myUserDetailsService() {
		return new MyUserDetailsService();
	}
}
