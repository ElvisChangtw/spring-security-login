package com.example.demo.service.impl;

import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.RolePermissionRepository;
import com.example.demo.domain.entity.RolePermission;
import com.example.demo.service.interfaces.RolePermissionService;

@Service
@Transactional
public class RolePermissionServiceImpl implements RolePermissionService{
	@Autowired
	RolePermissionRepository accessRepository;
	
	
	@Override
	public List<RolePermission> findByRoleIdIn(Collection<? extends Integer> roleId){
		return accessRepository.findByRolePermissionUPKRoleIdIn(roleId);
	}
	
	
}
