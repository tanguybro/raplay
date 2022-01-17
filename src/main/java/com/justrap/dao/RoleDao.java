package com.justrap.dao;

import java.util.List;

import com.justrap.model.Role;
import com.justrap.model.User;

public interface RoleDao {
	Role getRoleByName(String roleName);
	List<User> getUsersWithRole(String roleName); 
	void createRole(Role role);
	void updateRole(Role role);
	void deleteRole(String roleName);
}
