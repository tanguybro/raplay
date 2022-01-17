package com.justrap.controller;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.justrap.config.WebMvcConfig;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {WebMvcConfig.class})
@WebAppConfiguration // because there is @EnableWebMvc in WebMvcConfig.java
class LoginControllerTest {
	
	@Autowired
    private WebApplicationContext webApplicationContext;
	private MockMvc mockMvc;
	
	@BeforeEach
	public void setWac(){
	    this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
	}

	@Test
	void testValidLogin() throws Exception {
//		assertThat(controller).isNotNull();
		assertNotNull(mockMvc);
		mockMvc.perform(get("/login"))
			.andExpect(status().isOk())
	        .andExpect(forwardedUrl("/WEB-INF/views/login.jsp"));
		
		mockMvc.perform(post("/login").param("inputEmail", "toto@gmail.com")
			.param("inputPassword", "tototo"))
			.andExpect(status().isOk())
			.andExpect(forwardedUrl("/WEB-INF/views/upload.jsp"));
	}
	
	@Test
	void testInvalidLogin() throws Exception {
		assertNotNull(mockMvc);
		mockMvc.perform(post("/login").param("inputEmail", "toto@gmail.com")
			.param("inputPassword", "WrongPassword"))
			.andExpect(status().isOk())
			.andExpect(forwardedUrl("/WEB-INF/views/login.jsp"))
			.andExpect(MockMvcResultMatchers.model().attribute("errorMessage", "Identifiant/Mot de passe incorrect"));
	}
}
