package com.justrap.service;

import com.justrap.model.UserInterface;

public interface LoginService {
	
	/**
	 * @param mail
	 * @param password
	 * @return the User corresponding to the credentials | null credentials doesn't match
	 * @throws Exception
	 */
	UserInterface getUserByCredentials(String mail, String password) throws Exception;
}
