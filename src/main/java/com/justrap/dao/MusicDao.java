package com.justrap.dao;

import java.util.List;

import com.justrap.model.Music;

public interface MusicDao {
	void createMusic(Music music);
	void updateMusic(Music music);
	void deleteMusic(String name);
	Music getMusicByName(String name);
	List<Music> listMusics();
}
