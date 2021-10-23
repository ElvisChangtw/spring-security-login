package com.example.demo.service.interfaces;

import java.util.List;

import com.example.demo.domain.entity.Permission;

public interface PermissionService {
	public Permission findPermissionById(int id);
	public List<Permission> findPermissionByUserName(String userName);
	public List<Permission> findPermissionByRoleName(String roleName);
}
