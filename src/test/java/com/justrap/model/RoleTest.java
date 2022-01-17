package com.justrap.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.justrap.model.Role;

class RoleTest {

	@Test
	void test() {
		Role role = new Role();
		assertEquals(role.getUsers(), null);
		
	}

}
