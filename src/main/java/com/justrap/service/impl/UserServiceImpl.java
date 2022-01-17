package com.justrap.service.impl;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
import com.google.firebase.auth.UserRecord.CreateRequest;
import com.justrap.dao.UserDao;
import com.justrap.model.User;
import com.justrap.model.UserInterface;
import com.justrap.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDao dao;
	private UserInterface currentUser;
	
	@Override
	public UserInterface getCurrentUser() {
		return currentUser;
	}
	
	@Override
	public void signIn(UserInterface user) {
		currentUser = user;
	}
	
	@Override
	public void signOut() {
		currentUser = null;
	}

	@Override
	public UserInterface getUserById(String id) {
		return dao.getUserById(id);
	}

	@Override
	public UserInterface createUser(String password, String username, String mail) {
		UserRecord userRecord = null;
		CreateRequest request = new CreateRequest()
				.setPassword(password)
				.setEmail(mail);
		try {
			userRecord = FirebaseAuth.getInstance().createUser(request);
		} catch (FirebaseAuthException e) {
			e.printStackTrace();
		}
		User newUser = new User(userRecord.getUid(), username, mail);
		dao.createUser(newUser);
		return newUser;
	}
	
	@Override
	public boolean deleteUser(String id) {
		try {
			FirebaseAuth.getInstance().deleteUser(id);
		} catch (FirebaseAuthException e) {
			e.printStackTrace();
			return false;
		}
		dao.deleteUser(id);
		return true;
	}
	
	@Override
	public void updateUsernameUser(String id, String username) {
		UserInterface user = getUserById(id);
		user.setUsername(username);
		dao.updateUser((User) user);
	}
	
	@Override
	public List<User> getUsersList() {
		return dao.allUsers();
	}
	
	@Override
	public List<User> getFilteredUsers(String pattern){
		List<User> usersList = dao.allUsers();
		Iterator<User> iterator = usersList.iterator();
		while (iterator.hasNext()) {
		   User user = iterator.next();
		   if(!user.getUsername().contains(pattern)) {
			   iterator.remove();
		   }
		}
		return usersList;
	}
		
}
