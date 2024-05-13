package com.springBoot.EWDJexamenopdracht;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/login")
public class LoginController {
	
	@Autowired
    private MessageSource messageSource;

	@GetMapping
	public String showLoginPage(@RequestParam(value = "error", required = false) String error, @RequestParam(value = "logout", required = false) String logout, Model model, Locale locale) {
			
		if (error != null) {
			model.addAttribute("error", messageSource.getMessage("login.validation.invalid", null, locale));
		}
		
		if (logout != null) {
            model.addAttribute("msg", messageSource.getMessage("logout.confirmation", null, locale));
        }
		
		return "loginForm";
	}

	@PostMapping
	public String onSubmit() {
		return "redirect:/sports";
	}
}
