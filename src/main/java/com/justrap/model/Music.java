package com.justrap.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;


@Entity
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
public abstract class Music {
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "duration_seconds")
	private int durationInSeconds;
	
	@Column(name = "release_date")
	private Date releaseDate;
	
	@Column(name = "format")
	private String format;
	
	@ManyToOne(fetch = FetchType.EAGER)
	private User artist;
	
	public Music() {}
	
	public Music(String name, int duration, String format, User artist) {
		this.name = name;
		this.durationInSeconds = duration;
		this.format = format;
		this.artist = artist;
		releaseDate = new Date();
	}
	
	public int getId() {
		return id;
	}
	
	public String getFormat() {
		return format;
	}

}
