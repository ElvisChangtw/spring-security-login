package com.example.demo.domain.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.example.demo.domain.entity.compositekey.RolePermissionUPK;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name="role_permission")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RolePermission {
		
		@EmbeddedId
		private RolePermissionUPK rolePermissionUPK;

		@Override
		public String toString() {
			return "UserRole [user_id=" + rolePermissionUPK.getRoleId() 
			+ ", role_id=" + rolePermissionUPK.getPermissionId() + "]";
		}
		
	
	
}
