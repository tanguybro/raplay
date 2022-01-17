package com.justrap.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.justrap.dao.MusicDao;
import com.justrap.model.Music;

@Repository
public class MusicDaoImpl implements MusicDao {
	
    @Autowired
    private SessionFactory sessionFactory;
		
	@Override
	@Transactional
	public void createMusic(Music music) {
		Session session = sessionFactory.getCurrentSession();
		session.persist(music);
		session.flush();
	}

	@Override
	public void updateMusic(Music music) {
		sessionFactory.getCurrentSession().update(music);
	}

	@Override
	public void deleteMusic(String name) {
		Session session = sessionFactory.getCurrentSession();
		Music music = getMusicByName(name);
		if(null != music)
			session.delete(music);
	}

	@Override
	public Music getMusicByName(String name) {
		Session session = sessionFactory.getCurrentSession();
		return session.byNaturalId(Music.class)
				.using("name", name)
				.load();
	}

	@SuppressWarnings("unchecked")
	@Transactional
	@Override
	public List<Music> listMusics() {
		Session session = sessionFactory.getCurrentSession();
		List<Music> musicsList = session.createQuery("from Music").list();
		return musicsList;
	}

}
