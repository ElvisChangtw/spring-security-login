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
		// ?????????User??????org.springframework.security.core.userdetails.User
		// ???UserDetails???????????????????????????????????????????????????????????????
	}

	// ?????????????????????user
	@Override
	public User getCurrentUser() { // ??????????????????????????????
		SecurityContext context = SecurityContextHolder.getContext(); // ???ContextHolder?????????SecurityContext??????
		Authentication auth = context.getAuthentication(); // ???SecurityContext????????????Authentication??????
		// ??????????????????????????????
		if (auth == null) {
			return null;
		} else if (!(auth.getPrincipal() instanceof User)) {
			return null;
		}

		User user = (User) auth.getPrincipal();
		// principal: ????????????????????? "????????????id"???????????????????????? "??????????????? UserDetails"
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

	// ????????????
	@Override
	public void updateAuthority() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		// ??????authentication????????????AppUser
		AppUser user = appUserRepository.findByUsername(auth.getName());
		// ????????????????????????????????????
		if (user != null) {
			Set<GrantedAuthority> updatedAuthorities = new HashSet<GrantedAuthority>();
			// ???user????????????role
			for (Role role : user.getRoles()) {
				String roleAuthority = "ROLE_" + role.getRole();
				// ???????????????permission
				List<Permission> permissions = role.getPermissions();
				for (Permission permission : permissions) {
					String authority = permission.getPermission();
					// permission??????GrantedAuthority
					updatedAuthorities.add(new SimpleGrantedAuthority(authority));
				}
				// ???role????????????GrantedAuthority
				updatedAuthorities.add(new SimpleGrantedAuthority(roleAuthority));
				Authentication newAuth = new UsernamePasswordAuthenticationToken(auth.getPrincipal(),
						auth.getCredentials(), updatedAuthorities);
				SecurityContextHolder.getContext().setAuthentication(newAuth);
			}
		}
	}
}