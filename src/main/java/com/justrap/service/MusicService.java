package com.justrap.service;

import java.io.File;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.justrap.exception.InvalidExtensionException;
import com.justrap.model.Music;
import com.justrap.model.UserInterface;

public interface MusicService {
	
	/**
	 * Create a music in database and store the file in firebase
	 * @param file
	 * @param isSong
	 * @param musicName
	 * @param artist
	 * @throws InvalidExtensionException
	 */
	void createMusicByFile(MultipartFile file, boolean isSong, String musicName, UserInterface artist) throws InvalidExtensionException;

	/**
	 * Get the music file
	 * @param music
	 * @return the audio file corresponding to the music
	 */
	File getFile(Music music);
	
	/**
	 * @param name
	 * @return list of the musics named by the string given
	 */
	List<Music> getMusicsByName(String name);
}
