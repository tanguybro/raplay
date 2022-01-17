package com.justrap.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.justrap.dao.RoleDao;
import com.justrap.model.Role;
import com.justrap.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {
	
	@Autowired
	private RoleDao dao;

	@Override
	public void createRole(String roleName) {
		dao.createRole(new Role(roleName));	
	}
	


}
