package com.springBoot.EWDJexamenopdracht;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Autowired
	private UserDetailsService uds;
	
	@Autowired
	public void configueGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(uds).passwordEncoder(new BCryptPasswordEncoder());
	}
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.csrf(csrf -> csrf.csrfTokenRepository(new HttpSessionCsrfTokenRepository()))
			.authorizeHttpRequests(requests ->
					requests
					.requestMatchers("/login**").permitAll()
					.requestMatchers("/css/**").permitAll()
					.requestMatchers("/fonts/**").permitAll()
					.requestMatchers("/images/**").permitAll()
					.requestMatchers("/403**").permitAll()
					.requestMatchers("/*").hasAnyRole("USER", "ADMIN")
					.requestMatchers("/sports/").hasAnyRole("USER", "ADMIN")
					.requestMatchers("/sports/*/games").hasAnyRole("USER", "ADMIN")
					.requestMatchers("/sports/*/games/add").hasAnyRole("ADMIN")
					.requestMatchers("/myTickets**").hasAnyRole("USER")
					.requestMatchers("/sports/*/games/*/buy").hasAnyRole("USER"))
			.formLogin(form ->
					form.defaultSuccessUrl("/sports", true)
						.loginPage("/login")
						.usernameParameter("email")
						.passwordParameter("password"))
			.exceptionHandling(handling -> handling.accessDeniedPage("/403"));
		return http.build();
	}
}
