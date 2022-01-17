package com.justrap.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.justrap.dao.RoleDao;
import com.justrap.model.Role;
import com.justrap.model.User;

@Repository
public class RoleDaoImpl implements RoleDao {
	
    @Autowired
    private SessionFactory sessionFactory;
		
	@Override
	@Transactional
	public Role getRoleByName(String roleName) {
		Session session = sessionFactory.getCurrentSession();
		return session.byNaturalId(Role.class)
               .using("name", roleName)
               .load();
	}
	
	@Override
	@Transactional
	public List<User> getUsersWithRole(String roleName) {
		return getRoleByName(roleName).getUsers();
	}
	
	@Override
	@Transactional
	public void createRole(Role role) {
		sessionFactory.getCurrentSession().persist(role);
	}

	@Override
	public void updateRole(Role role) {
		sessionFactory.getCurrentSession().update(role);
	}

	@Override
	public void deleteRole(String roleName) {
		Session session = sessionFactory.getCurrentSession();
		Role role = getRoleByName(roleName);
		if(null != role)
			session.delete(role);
	}

}
