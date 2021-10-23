package com.example.demo.dao;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.entity.RolePermission;

@Repository
public interface RolePermissionRepository extends JpaRepository<RolePermission, Long> {
	
	// 業務流串接
	List<RolePermission> findByRolePermissionUPKRoleIdIn(Collection<? extends Integer> roleId);
}
