package com.example.demo.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.UserRoleRepository;
import com.example.demo.domain.entity.UserRole;
import com.example.demo.service.interfaces.UserRoleService;

@Service
@Transactional
public class UserRoleServiceImpl implements UserRoleService{
	@Autowired
	UserRoleRepository userRoleRepository;
	@Override
	public List<UserRole> findByUserRoleUPKUserId(int userId) {
		return userRoleRepository.findByUserRoleUPKUserId(userId);
	}
}
