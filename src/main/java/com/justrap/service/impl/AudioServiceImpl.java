package com.justrap.service.impl;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.justrap.service.AudioService;
import com.justrap.utils.AudioPlayer;
import com.justrap.utils.AudioPlayerFactory;

@Service
public class AudioServiceImpl implements AudioService {
	
	@Autowired
	private AudioPlayerFactory factory;

	private AudioPlayer audioPlayer;

	@Override
	public void play(File file) {
		audioPlayer = factory.getPlayer(file);
		audioPlayer.play();
	}

	@Override
	public void stop() {
		if(audioPlayer != null)
			audioPlayer.stop();
	}

}
