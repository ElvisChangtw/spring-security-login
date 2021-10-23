package com.example.demo.domain.entity.compositekey;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
@EqualsAndHashCode
public class RolePermissionUPK implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5815817524497040023L;

	/**
	 * 
	 */
	
	@Column(name="role_id", nullable=false)
	private int roleId;
	
	@Column(name="permission_id", nullable=false)
	private int permissionId;
	
	
}
