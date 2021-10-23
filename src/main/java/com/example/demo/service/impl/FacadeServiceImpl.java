package com.example.demo.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.entity.AppUser;
import com.example.demo.domain.entity.Permission;
import com.example.demo.domain.entity.Role;
import com.example.demo.domain.entity.RolePermission;
import com.example.demo.domain.entity.UserRole;
import com.example.demo.service.interfaces.FacadeService;

@Service
@Transactional
public class FacadeServiceImpl implements FacadeService{
	@Autowired
	AppUserDetailsServiceImpl appUserDetailsService;
	@Autowired
	RoleServiceImpl roleService;
	@Autowired
	UserRoleServiceImpl userRoleService;
	@Autowired
	RolePermissionServiceImpl rolePermissionService;
	@Autowired
	PermissionServiceImpl permissionService;
	
	// 取得現在登入的user可訪問的url(resolve前)
	// 從權限找
	public Set<String> getAccessibleUrls() {
		Set<String> urls = new HashSet<String>();
		// 取得現行使用者資訊
		User user = appUserDetailsService.getCurrentUser();
		
		if(user == null) {	// 判斷沒登入時回傳null
			return null;
		}
		AppUser appUser = appUserDetailsService.findByUsername(user.getUsername());
		List<Role> roles = appUser.getRoles();
		for(Role role : roles) {
			List<Permission> permissions = role.getPermissions();
			for(Permission permission : permissions) {
				String url = permission.getUrl();
				urls.add(url);
			}
		}
		return urls;
	}
	// native SQL
	public Set<String> getAccessibleUrls2(){
		// 取得現行使用者資訊
		User user = appUserDetailsService.getCurrentUser();
		if(user == null ) {
			return null;
		}
		Set<String> urls = new HashSet<String>();
		// 透過native SQL join/subquery 查詢出permission物件
		List<Permission> permissions = permissionService.findPermissionByUserName(user.getUsername());
		// 遍歷取出permission名稱
		for (Permission permission : permissions) {
			String url = permission.getUrl();
			urls.add(url);
		}
		return urls;
	}

	// 業務流
	public Set<String> getAccessibleUrls3() {
		Set<String> urls = new HashSet<String>();
		// 取得現行使用者資訊
		User user = appUserDetailsService.getCurrentUser();
		if(user == null ) {
			return null;
		}
		// 找出appUser物件
		AppUser appUser = appUserDetailsService.findByUsername(user.getUsername());
		
		// 透過AppUser的id找到User_Role列表
		List<UserRole> userRoles = userRoleService.findByUserRoleUPKUserId(appUser.getId());
		// 透過User_Role找到RoleId
		List<Integer> userRoleIds = new ArrayList<Integer>();
		for(UserRole userRole : userRoles) {
			userRoleIds.add(userRole.getUserRoleUPK().getRoleId());
		}
		// 透過role_id找到RolePermission
		List<RolePermission> rolePermissions = rolePermissionService.findByRoleIdIn(userRoleIds);
		
		// 透過Role_Permission找到PermissionId
		List<Integer>  RolePermissionIds = new ArrayList<Integer>();
		for (RolePermission rolePermission : rolePermissions) {
			RolePermissionIds.add(rolePermission.getRolePermissionUPK().getPermissionId());
		}
		// 透過permission_id找到對應的url
		for(int permissionId : RolePermissionIds) {
			String url = permissionService.findPermissionById(permissionId).getUrl();
			urls.add(url);
		}
		return urls;
	}
	
	@PostFilter("hasRole('ADMIN')")
//	// @PostFilter("filterObject.getusername() == authentication.principal.username")
//  // @PostFilter 可以攔截執行該Method之後所回傳內容(必須為Collection集合)，並賦予權限或篩選特定條件。
	public List<AppUser> findAllUsers(){
		List<AppUser> userList = appUserDetailsService.findAllUsers();
		return userList;
	}
	
	public User getCurrentUser(){
		return appUserDetailsService.getCurrentUser();
	}
	public void updateAuthority() {
		appUserDetailsService.updateAuthority();
	}
	
}
