package com.example.demo.service.interfaces;

import java.util.Collection;
import java.util.List;

import com.example.demo.domain.entity.RolePermission;

public interface RolePermissionService {

	List<RolePermission> findByRoleIdIn(Collection<? extends Integer> roleId);

}
