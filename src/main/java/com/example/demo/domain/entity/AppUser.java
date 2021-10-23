package com.example.demo.domain.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Entity
@Table(name="user")
@Data
@Builder
@AllArgsConstructor
public class AppUser {
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(length=50)
	private String username;
	@Column(length=50)
	private String password;
	@Column(length=50)
	private boolean enabled;
	@Column(length=20)
	private String firstName;
	@Column(length=20)
	private String lastName;
	
//	@OneToMany(mappedBy = "appUser", fetch = FetchType.EAGER)
//	// mappedBy為entity 名稱
//    private List<UserRole> authorities;
	@ManyToMany
	@JoinTable(name="user_role",
	joinColumns= @JoinColumn(name="user_id"),
	inverseJoinColumns = @JoinColumn(name= "role_id"))
	private List<Role> roles;
	
	public AppUser(int id, String username, String password, boolean enabled, String firstName, String lastName) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.enabled = enabled;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public AppUser() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	
	
	
}
