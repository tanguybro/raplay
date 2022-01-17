package com.justrap.utils;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.justrap.manager.FileManager;
import com.justrap.utils.impl.Mp3Player;

@Component
public class AudioPlayerFactory {
	
	@Autowired
	private FileManager fileManager;
	
	public AudioPlayer getPlayer(File file) {
		if(fileManager.getExtension(file).equals("mp3"))
			return new Mp3Player(file);
		else
			throw new RuntimeException("Fichier invalide");
	}

}
