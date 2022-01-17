package com.justrap.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "BEAT")
public class Beat extends Music {

	public Beat(String name, int duration, String format, User artist) {
		super(name, duration, format, artist);
	}
	
	public Beat() {}

}
