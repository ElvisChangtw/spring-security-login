package com.example.demo.service.interfaces;

import java.util.List;

import com.example.demo.domain.entity.Role;

public interface RoleService {

	List<Role> findAllRoles();

	Role findRoleById(int id);

	Role findByRoleName(String name);

}
