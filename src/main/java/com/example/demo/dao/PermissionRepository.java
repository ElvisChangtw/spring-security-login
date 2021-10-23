package com.example.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.domain.entity.Permission;

public interface PermissionRepository extends JpaRepository<Permission, Long> {
	
	Permission findById(int id);
	
	
	@Query(value = " select * from permission where id in (\n"
			+ "	select permission_id  from role_permission where role_id in (\n"
			+ "		select role_id from user_role where user_id = (\n"
			+ "			select id from user where username = :userName)))"
			, nativeQuery= true)
	List<Permission> findbyUserName(String userName);
	
	
	
	@Query(value = " \n"
			+ "select * from permission where id in (\n"
			+ "	select permission_id from role_permission where role_id = (\n"
			+ "        select id from role where role = :roleName)\n"
			+ "	)"
			, nativeQuery= true)
	List<Permission> findbyRole(String roleName);
	
	
}

