package com.example.demo.util;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.example.demo.dao.AppUserRepository;
import com.example.demo.domain.entity.AppUser;
import com.example.demo.domain.entity.Permission;
import com.example.demo.service.impl.PermissionServiceImpl;
@Component
public class UserAuthorityUtils {
	@Autowired AppUserRepository appUserRepository;
	@Autowired PermissionServiceImpl permissionService;
	
	// 塞三筆假user資料進db
	public void createMockData() {
		// 塞假資料進DB
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		AppUser user1 = AppUser.builder()
				.username("user")
				.password(passwordEncoder.encode("user"))
				.enabled(true)
				.firstName("user")
				.lastName("1")
				.build()
				
				, user2= 
				AppUser.builder()
				.username("admin")
				.password(passwordEncoder.encode("admin"))
				.enabled(true)
				.firstName("admin")
				.lastName("2")
				.build()

				, user3= 
				AppUser.builder()
				.username("staff")
				.password(passwordEncoder.encode("staff"))
				.enabled(true)
				.firstName("staff")
				.lastName("3")
				.build();
		appUserRepository.save(user1);
		appUserRepository.save(user2);
		appUserRepository.save(user3);
	}
	
	public String[] getUrlsFromDb(String roleName){
		Set<String> urls = new HashSet<String>();
		List<Permission> permissions = permissionService.findPermissionByRoleName(roleName);
		for(Permission permission : permissions) {
			urls.add("/" + permission.getUrl());
		}
		return urls.toArray(new String[urls.size()]);
	}
}