package com.example.demo.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.dao.AppUserRepository;
import com.example.demo.domain.entity.AppUser;
import com.example.demo.domain.entity.Permission;
import com.example.demo.domain.entity.Role;
import com.example.demo.service.interfaces.AppUserDetailsService;

@Service
@Transactional
public class AppUserDetailsServiceImpl implements UserDetailsService, AppUserDetailsService {
	@Autowired
	private AppUserRepository appUserRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		final String rolePrefix = "ROLE_";
		AppUser user = appUserRepository.findByUsername(username);

		if (user == null) {
			throw new UsernameNotFoundException("Invalid username/password");
		}
		Set<GrantedAuthority> grantedAuthorities = new HashSet<GrantedAuthority>();
		for (Role role : user.getRoles()) {
			String roleAuthority = rolePrefix + role.getRole();
			List<Permission> permissions = role.getPermissions();
			for (Permission permission : permissions) {
				String authority = permission.getPermission();
				grantedAuthorities.add(new SimpleGrantedAuthority(authority));
			}

//        	System.out.println(authorityName);
			grantedAuthorities.add(new SimpleGrantedAuthority(roleAuthority));

		}

		return new User(user.getUsername(), user.getPassword(), grantedAuthorities);
		// 這邊的User是指org.springframework.security.core.userdetails.User
		// 是UserDetails介面的實作，儲存使用者名稱、密碼及擁有權限
	}

	// 取得現在登入的user
	@Override
	public User getCurrentUser() { // 取得已登入使用者物件
		SecurityContext context = SecurityContextHolder.getContext(); // 從ContextHolder中取得SecurityContext物件
		Authentication auth = context.getAuthentication(); // 從SecurityContext物件取得Authentication物件
		// 檢查是否有使用者登入
		if (auth == null) {
			return null;
		} else if (!(auth.getPrincipal() instanceof User)) {
			return null;
		}

		User user = (User) auth.getPrincipal();
		// principal: 認証成功前存放 "用戶登入id"，認証成功後存放 "用戶對應的 UserDetails"
		return user;
	}

	@Override
	public AppUser findByUsername(String username) {
		return appUserRepository.findByUsername(username);
	}

	@Override
	public List<AppUser> findAllUsers() {
		return appUserRepository.findAll();
	}

	// 更新權限
	@Override
	public void updateAuthority() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		// 透過authentication物件取得AppUser
		AppUser user = appUserRepository.findByUsername(auth.getName());
		// 若有現行登入使用者則繼續
		if (user != null) {
			Set<GrantedAuthority> updatedAuthorities = new HashSet<GrantedAuthority>();
			// 從user物件取出role
			for (Role role : user.getRoles()) {
				String roleAuthority = "ROLE_" + role.getRole();
				// 從角色取出permission
				List<Permission> permissions = role.getPermissions();
				for (Permission permission : permissions) {
					String authority = permission.getPermission();
					// permission塞進GrantedAuthority
					updatedAuthorities.add(new SimpleGrantedAuthority(authority));
				}
				// 將role名稱塞進GrantedAuthority
				updatedAuthorities.add(new SimpleGrantedAuthority(roleAuthority));
				Authentication newAuth = new UsernamePasswordAuthenticationToken(auth.getPrincipal(),
						auth.getCredentials(), updatedAuthorities);
				SecurityContextHolder.getContext().setAuthentication(newAuth);
			}
		}
	}
}