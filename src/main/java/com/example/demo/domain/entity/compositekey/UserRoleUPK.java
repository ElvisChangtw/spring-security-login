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
public class UserRoleUPK implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3461477798304939610L;

	@Column(name="user_id", nullable=false)
	private int userId;
	
	@Column(name="role_id", nullable=false)
	private int roleId;
	
	
}
