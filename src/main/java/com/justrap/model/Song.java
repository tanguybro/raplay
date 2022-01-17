package com.justrap.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "SONG")
public class Song extends Music {
	
	public Song(String name, int duration, String format, User artist) {
		super(name, duration, format, artist);
	}
	
	public Song() {}

}
