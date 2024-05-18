package com.springBoot.EWDJexamenopdracht;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
class LoginControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@Test
	public void getLoginPage() throws Exception {
		mockMvc.perform(get("/login")).andExpect(status().isOk()).andExpect(view().name("loginForm"))
		.andExpect(MockMvcResultMatchers.model().attributeDoesNotExist("error"))
		.andExpect(MockMvcResultMatchers.model().attributeDoesNotExist("msg"));
	}
	
	@Test
	public void testWrongPassword () throws Exception {
		mockMvc.perform(formLogin("/login")
				.user("email", "user@javaweb.com")
				.password("password", "wrongpassword"))
				.andExpect(status().isFound())
				.andExpect(redirectedUrl("/login?error"));
	}
	
	@Test
    void testCorrectPassword() throws Exception {
        mockMvc.perform(formLogin("/login")
            .user("email", "user@javaweb.com")
            .password("password", "Password"))
            .andExpect(status().isFound())
            .andExpect(redirectedUrl("/sports"));
    }
	
	@Test
    public void testLoginWithError() throws Exception {
        mockMvc.perform(get("/login?error=true"))
                .andExpect(status().isOk())
                .andExpect(view().name("loginForm"))
                .andExpect(MockMvcResultMatchers.model().attribute("error", "Invalid email or password!"))
                .andExpect(MockMvcResultMatchers.model().attributeDoesNotExist("msg"));
    }
	
	@Test
    public void testLoginWithLogout() throws Exception {
        mockMvc.perform(get("/login?logout=true"))
                .andExpect(status().isOk())
                .andExpect(view().name("loginForm"))
                .andExpect(MockMvcResultMatchers.model().attributeDoesNotExist("error"))
                .andExpect(MockMvcResultMatchers.model().attribute("msg", "You have successfully logged out."));
    }

}
