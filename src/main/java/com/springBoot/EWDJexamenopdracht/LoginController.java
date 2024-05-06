package com.springBoot.EWDJexamenopdracht;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/login")
public class LoginController {

	@GetMapping
	public String showLoginPage(@RequestParam(value = "error", required = false) String error, @RequestParam(value = "logout", required = false) String logout, Model model) {
		
		// TODO moeten deze berichten ook uit messages.properties komen??
		if (error != null) {
			model.addAttribute("error", "Invalid email or password!");
		}
		
		if (logout != null) {
            model.addAttribute("msg", "You've successfully logged out.");
        }
		
		return "loginForm";
	}

	@PostMapping
	public String onSubmit() {
		return "redirect:/sports";
	}
}
