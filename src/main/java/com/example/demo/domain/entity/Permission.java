package com.example.demo.domain.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="permission")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Permission {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private int id;
	
	@Column
	private String url;
	
	@Column
	private String permission;
	
	@ManyToMany(mappedBy = "permissions")
	private List<Role> roles;

	@Override
	public String toString() {
		return "Permission [id=" + id + ", url=" + url + ", permission=" + permission + "]";
	}
	
}
