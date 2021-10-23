package com.example.demo.service.interfaces;

import java.util.List;

import com.example.demo.domain.entity.UserRole;

public interface UserRoleService {
	public List<UserRole> findByUserRoleUPKUserId(int userId);
}
