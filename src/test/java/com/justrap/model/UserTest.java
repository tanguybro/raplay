package com.justrap.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.justrap.model.User;

class UserTest {

	@Test
	void test() {
		User user = new User();
		assertEquals(user.getUsername(), null);
		assertEquals(user.getMail(), null);
		assertEquals(user.toString(), null);
		
		String usernameTest = "usernameTest";
		user.setUsername(usernameTest);
		assertEquals(user.getUsername(), usernameTest);
		assertEquals(user.getUsername(), user.toString());
		
		String mailTest = "mailTest";
		user.setMail(mailTest);
		assertEquals(user.getMail(), mailTest);
		
		
		User user2 = new User("1", "usernameTest2", "mailTest2");
		assertTrue(user2.equals(user2));
		assertNotEquals(user, user2);
		assertFalse(user.equals(user2));
		User user3 = new User("1", null, null);
		assertTrue(user2.equals(user3));
		
		
		user = new User("10", usernameTest+"v2", mailTest+"v2");
		assertEquals(user.getUsername(), usernameTest+"v2");
		assertEquals(user.getUsername(), user.toString());
		
		assertEquals(user.getMail(), mailTest+"v2");
		
	}

}
