package com.springBoot.EWDJexamenopdracht;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import service.GameService;
import service.GameServiceImpl;
import service.MyUserDetailsService;
import service.TicketService;
import service.TicketServiceImpl;
import validator.GameValidator;
import validator.TicketValidator;

@SpringBootApplication
@EnableJpaRepositories("repository")
@EntityScan("domain")
public class EwdJexamenopdrachtApplication implements WebMvcConfigurer {

	public static void main(String[] args) {
		SpringApplication.run(EwdJexamenopdrachtApplication.class, args);
	}
	
	
	@Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addRedirectViewController("/", "/sports");
        registry.addViewController("/403").setViewName("403");
    }
	
	@Bean 
	DateFormatter dateFormatter() {
		return new DateFormatter();
	}
	
	@Bean
	GameValidator gameValidation() {
		return new GameValidator();
	}
	
	@Bean
	TicketValidator ticketValidation() {
		return new TicketValidator();
	}
	
	@Bean
	UserDetailsService myUserDetailsService() {
		return new MyUserDetailsService();
	}
	
	@Bean
	TicketService ticketService() {
		return new TicketServiceImpl();
	}
	
	@Bean 
	GameService gameService() {
		return new GameServiceImpl();
	}
}
