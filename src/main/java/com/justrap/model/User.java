package com.justrap.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "USERS")
public class User implements UserInterface {
	
	@Id
	@Column(name = "id")
	private String id;
	
	@Column(name = "username")
	private String username;
	
	@Column(name = "mail", unique = true)
	private String mail;
	
    @ManyToMany
    @JoinTable( 
        name = "USER_ROLE", 
        joinColumns = @JoinColumn(
          name = "user_id", referencedColumnName = "id"), 
        inverseJoinColumns = @JoinColumn(
          name = "role_id", referencedColumnName = "id")) 
    private List<Role> roles;
	
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER, mappedBy = "artist")
    private List<Music> musics;
    
    public User() {}

    public User(String id, String username, String mail) {
		this.id = id;
		this.username = username;
		this.mail = mail;
		this.roles = new ArrayList<Role>();
		this.musics = new ArrayList<Music>();
	}
    
    @Override
    public String toString() {
    	return username;
    }
    
    @Override
	public boolean equals(Object obj) {
		if (this == obj) {
            return true;
        }
        if (this.id == null) {
            return false;
        }
        if (obj instanceof User && obj.getClass().equals(getClass())) {
            return this.id.equals(((User) obj).id);
        }

        return false;
	}
	
	@Override
	public String getUsername() {
		return username;
	}
	
	@Override
	public void setUsername(String username) {
		this.username = username;
	}
	
	@Override
	public String getMail() {
		return mail;
	}
	
	public void setMail(String mail) {
		this.mail = mail;
	}
	
	@Override
	public void addMusic(Music music) {
		musics.add(music);
	}
	
}
