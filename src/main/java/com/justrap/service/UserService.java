package com.justrap.service;

import java.util.List;

import com.justrap.model.User;
import com.justrap.model.UserInterface;

public interface UserService {
	
	/**
	 * @return the current user | null if no user is connected
	 */
	UserInterface getCurrentUser();
	
	/**
	 * Set the current user
	 * @param user
	 */
	void signIn(UserInterface user);
	
	/**
	 * Set the current user to null
	 */
	void signOut();
	
	/**
	 * @param id
	 * @return the user corresponding to the id | null if not found
	 */
	UserInterface getUserById(String id);
	
	/**
	 * Create an user in firebase and in the database
	 * @param username
	 * @param password
	 * @param mail
	 * @return the created user
	 */
	UserInterface createUser(String username, String password, String mail);
	
	/**
	 * Retrieve all the users
	 */
	List<User> getUsersList();
	
	/**
	 * Retrieve all users who match the pattern
	 * @param pattern
	 */
	List<User> getFilteredUsers(String pattern);

	/**
	 * Delete an user in firebase and in the database
	 * @param id
	 * @return true if the user was deleted
	 */
	boolean deleteUser(String id);
	
	/**
	 * Update an user in firebase and in the database
	 * @param id
	 * @param username
	 */
	void updateUsernameUser(String id, String username);
}
