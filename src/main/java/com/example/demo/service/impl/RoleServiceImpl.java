package com.example.demo.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.RoleRepository;
import com.example.demo.domain.entity.Role;
import com.example.demo.service.interfaces.RoleService;

@Service
@Transactional
public class RoleServiceImpl implements RoleService{
	@Autowired
	RoleRepository roleRepository;
	
	
	@Override
	public List<Role> findAllRoles(){
		return roleRepository.findAll();
	}
	@Override
	public Role findRoleById(int id) {
		return roleRepository.findById(id);
	}
	@Override
	public Role findByRoleName(String name) {
		return roleRepository.findByRole(name);
	}
	
}
