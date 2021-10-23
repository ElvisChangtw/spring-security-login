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
import lombok.NoArgsConstructor;

@Entity
@Table(name="role")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Role {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private int id;
	
	@Column
	private String role;
	
	@ManyToMany(mappedBy = "roles")
	private List<AppUser> users;
	
	@ManyToMany
	@JoinTable(name="role_permission",
	joinColumns= @JoinColumn(name="role_id"),
	inverseJoinColumns = @JoinColumn(name= "permission_id"))
	private List<Permission> permissions;

	@Override
	public String toString() {
		return "Role [id=" + id + ", role=" + role + "]";
	}
	

	
}
