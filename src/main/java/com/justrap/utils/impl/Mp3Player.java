package com.justrap.utils.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import com.justrap.utils.AudioPlayer;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.advanced.AdvancedPlayer;
import javazoom.jl.player.advanced.PlaybackEvent;
import javazoom.jl.player.advanced.PlaybackListener;

public class Mp3Player implements AudioPlayer, Runnable {

	private AdvancedPlayer player;
	private int pausedOnFrame;
	
	public Mp3Player(File file) {
		pausedOnFrame = 0;
		try {
			player = new AdvancedPlayer(new FileInputStream(file));
		} catch (FileNotFoundException | JavaLayerException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void play() {
		new Thread(this).start();
	}
	
	@Override
	public void stop() {
		player.stop();
	}
	
	@Override
	public void run() {
		player.setPlayBackListener(new PlaybackListener() {
		    @Override
		    public void playbackFinished(PlaybackEvent event) {
		        pausedOnFrame = event.getFrame();
		    }
		});
		try {
			player.play(pausedOnFrame, Integer.MAX_VALUE);
		} catch (JavaLayerException e) {
			e.printStackTrace();
		}
	}
	
}
