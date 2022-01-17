package com.justrap.service.impl;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.justrap.dao.MusicDao;
import com.justrap.exception.InvalidExtensionException;
import com.justrap.manager.FileManager;
import com.justrap.model.Beat;
import com.justrap.model.Music;
import com.justrap.model.Song;
import com.justrap.model.User;
import com.justrap.model.UserInterface;
import com.justrap.service.MusicService;

@Service
public class MusicServiceImpl implements MusicService {
	
	@Autowired
	private MusicDao dao;
	@Autowired
	private FileManager fileManager;
	
	@Override
	public void createMusicByFile(MultipartFile file, boolean isSong, String musicName, UserInterface artist) throws InvalidExtensionException {
		if(!fileManager.audioFileHasValidExtension(file))
			throw new InvalidExtensionException();
		int musicId = createMusic(isSong, musicName, 0, fileManager.getExtension(file), artist);
		fileManager.storeFile(file, musicId);
	}
	
	@Override
	public File getFile(Music music) {
		return fileManager.getFile(music.getId() + "." + music.getFormat());
	}

	private int createMusic(boolean isSong, String name, int duration, String format, UserInterface artist) {
		Music music = null;
		if(isSong)
			music = new Song(name, duration, format, (User) artist);
		else
			music = new Beat(name, duration, format, (User) artist);
		dao.createMusic(music);
		return music.getId();
	}

	@Override
	public List<Music> getMusicsByName(String name) {
		return dao.listMusics();
	}
	
}
