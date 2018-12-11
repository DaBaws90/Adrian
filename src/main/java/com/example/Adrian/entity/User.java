package com.example.Adrian.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="users")
public class User {
	
	@Id
	@Column(name="username", unique=true, nullable=false, length=45)
	private String username;
	
	@Column(name="password", nullable=false, length=60)
	private String password;
	
	@Column(name="active")
	private boolean active;
	
	@OneToMany(mappedBy="user", fetch=FetchType.EAGER)
	private List<UserRole> userRole = new ArrayList<UserRole>();

	public User() {
		super();
	}

	public User(String username, String password, boolean active, List<UserRole> userRole) {
		super();
		this.username = username;
		this.password = password;
		this.active = active;
		this.userRole = userRole;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public List<UserRole> getUserRole() {
		return userRole;
	}

	public void setUserRole(List<UserRole> userRole) {
		this.userRole = userRole;
	}

	@Override 
	// Si da error, podría ser porque desde aquí mostramos a los roles, y en roles mostramos el usuario, por lo ue Java detecta un loop al mostrar los datos
	public String toString() {
		return "User [username=" + username + ", password=" + password + ", active=" + active + ", userRole=" + userRole
				+ "]";
	}
	

}
