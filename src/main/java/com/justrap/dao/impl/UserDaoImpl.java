package com.justrap.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.justrap.dao.UserDao;
import com.justrap.model.User;
import com.justrap.model.UserInterface;

@Repository
public class UserDaoImpl implements UserDao {
	
    @Autowired
    private SessionFactory sessionFactory;
		
	@Override
	@Transactional
	public UserInterface getUserById(String id) {
		return sessionFactory.getCurrentSession().get(User.class, id);
	}
	
	@Override
	@Transactional
	public void createUser(User user) {
		sessionFactory.getCurrentSession().persist(user);
	}

	@Override
	@Transactional
	public void updateUser(User user) {
		sessionFactory.getCurrentSession().update(user);
	}

	@Override
	@Transactional
	public void deleteUser(String id) {
		Session session = sessionFactory.getCurrentSession();
		User user = (User) session.load(User.class, id);
		if(null != user)
			session.delete(user);
	}
	
	@SuppressWarnings("unchecked")
	@Transactional
	@Override
	public List<User> allUsers() {
		Session session = sessionFactory.getCurrentSession();
		List<User> usersList = session.createQuery("from User").list();
		return usersList;
	}
}
