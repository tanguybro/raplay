package com.justrap.service;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileInputStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.context.WebApplicationContext;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.justrap.config.WebMvcConfig;
import com.justrap.model.UserInterface;
import com.justrap.service.impl.UserServiceImpl;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {WebMvcConfig.class})
@WebAppConfiguration // because there is @EnableWebMvc in WebMvcConfig.java
class UserServiceImplTest {

	@Autowired
    private WebApplicationContext webApplicationContext;
	
	@Autowired
	private UserServiceImpl service;

	@BeforeEach
	public void init() throws Exception{
		if(FirebaseApp.getApps().isEmpty()) {
		    FileInputStream serviceAccount = new FileInputStream(webApplicationContext.getServletContext()
		    		.getRealPath("../../../WebContent/WEB-INF/serviceAccountKey.json"));
			FirebaseOptions options = new FirebaseOptions.Builder()
			  .setCredentials(GoogleCredentials.fromStream(serviceAccount))
			  .build();
			FirebaseApp.initializeApp(options);
		}
	}
	
	// Test of getCurrentUser()
	// Test of signIn(UserInterface user)
	// Test of signOut()
	@Test
	void testCurrentUser() throws FirebaseAuthException {
		assertFalse(FirebaseApp.getApps().isEmpty());
		
		assertEquals(service.getCurrentUser(), null);
		UserInterface totoUser = service.getUserById(FirebaseAuth.getInstance().getUserByEmail("toto@gmail.com").getUid());
		assertNotNull(totoUser);
		service.signIn(totoUser);
		assertNotNull(service.getCurrentUser());
		assertEquals(service.getCurrentUser().getMail(), totoUser.getMail());
		assertEquals(service.getCurrentUser().getUsername(), totoUser.getUsername());
		
		service.signOut();
		assertEquals(service.getCurrentUser(), null);

		service.signIn(totoUser);
		assertNotNull(service.getCurrentUser());
		assertEquals(service.getCurrentUser().getMail(), totoUser.getMail());
		service.signIn(null);
		assertEquals(service.getCurrentUser(), null);
	}
	
	// Test of getUsersList()
	// Test of createUser(String password, String username, String mail)
	// Test of deleteUser(String id)
	// Test of getFilteredUsers(String pattern)
	@Test
	void testUsers() throws Exception {
		assertFalse(FirebaseApp.getApps().isEmpty());
		UserInterface totoUser = service.getUserById(FirebaseAuth.getInstance().getUserByEmail("toto@gmail.com").getUid());
		
		assertNotNull(service.getUsersList());
		assertTrue(service.getUsersList().contains(totoUser));
		
		int nbUsers = service.getUsersList().size();
		UserInterface newUser = service.createUser("testPassword", "testUsername", "testMail@gmail.com");
		assertEquals(service.getUsersList().size(), nbUsers+1);
		service.deleteUser(FirebaseAuth.getInstance().getUserByEmail("testMail@gmail.com").getUid());
		assertEquals(service.getUsersList().contains(newUser), false);
		
		assertEquals(service.getFilteredUsers("to").contains(totoUser), true);
		assertEquals(service.getFilteredUsers("tota").contains(totoUser), false);
	}
	
	// Test of getUserById(String id)
	@Test
	void testGetUserById() throws FirebaseAuthException {
		assertFalse(FirebaseApp.getApps().isEmpty());
		String supposedTotoId = FirebaseAuth.getInstance().getUserByEmail("toto@gmail.com").getUid();
		assertNotNull(service.getUserById(supposedTotoId));
		assertEquals(service.getUserById(supposedTotoId).getMail(), "toto@gmail.com");
	}

}