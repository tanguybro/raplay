package com.justrap.dao;

import java.util.List;

import com.justrap.model.User;
import com.justrap.model.UserInterface;

public interface UserDao {
	UserInterface getUserById(String id);
	void createUser(User user);
	void updateUser(User user);
	void deleteUser(String id);
	List<User> allUsers();
}
