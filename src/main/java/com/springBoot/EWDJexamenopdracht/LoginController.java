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
	public String showLoginPage(@RequestParam(value = "logout", required = false) String logout, Model model) {
		
		if (logout != null) {
            model.addAttribute("msg", "You've been logged out successfully.");
        }
		
		return "loginForm";
	}

	@PostMapping
	public String onSubmit() {
		return "redirect:/sports";
	}
}
