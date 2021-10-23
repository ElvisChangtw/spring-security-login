package com.example.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dao.PermissionRepository;
import com.example.demo.domain.entity.Permission;
import com.example.demo.service.interfaces.PermissionService;

@Service
@Transactional
public class PermissionServiceImpl implements PermissionService{
	@Autowired
	PermissionRepository permissionRepository;
	
	public Permission findPermissionById(int id) {
		return permissionRepository.findById(id);
	}
	
	public List<Permission> findPermissionByUserName(String userName) {
		return permissionRepository.findbyUserName(userName);
	}
	
	public List<Permission> findPermissionByRoleName(String roleName){
		return permissionRepository.findbyRole(roleName);
	}
	
}
