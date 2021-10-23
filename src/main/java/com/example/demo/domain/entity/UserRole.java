package com.example.demo.domain.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.example.demo.domain.entity.compositekey.UserRoleUPK;

import lombok.Data;

@Entity
@Table(name="user_role")
@Data
public class UserRole {
	
	@EmbeddedId
	private UserRoleUPK userRoleUPK;

	@Override
	public String toString() {
		return "UserRole [user_id=" + userRoleUPK.getUserId() 
		+ ", role_id=" + userRoleUPK.getRoleId() + "]";
	}
	
	
}
