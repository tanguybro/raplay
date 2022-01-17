package com.justrap.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.NaturalId;

@Entity
@Table(name = "ROLE")
public class Role {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@NaturalId
	@Column(name = "name", unique = true)
	private String name;
	
	@ManyToMany(mappedBy = "roles")
    private List<User> users;
	
	public Role() {}
	
	public Role(String roleName) {
		name = roleName;
	}
	
	public List<User> getUsers() {
		return users;
	}

}
