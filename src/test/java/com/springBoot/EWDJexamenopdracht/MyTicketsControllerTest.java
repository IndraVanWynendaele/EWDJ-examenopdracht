package com.springBoot.EWDJexamenopdracht;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class MyTicketsControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@WithMockUser(username = "user@javaweb.com", roles = {"USER"})
	@Test
	public void testAccessMyTicketsPageCorrectRole() throws Exception {
		mockMvc.perform(get("/myTickets"))
				.andExpect(status().isOk())
				.andExpect(view().name("myTickets"))
				.andExpect(model().attributeExists("tickets"));
	}
	
	@WithMockUser(username = "admin@javaweb.com", roles = {"ADMIN"})
	@Test
	public void testAccessMyTicketsPageIncorrectRole() throws Exception {
		mockMvc.perform(get("/myTickets"))
				.andExpect(status().isForbidden());
	}
}
