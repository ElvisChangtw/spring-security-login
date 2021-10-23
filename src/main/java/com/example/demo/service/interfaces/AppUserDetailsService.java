package com.example.demo.service.interfaces;

import java.util.List;

import org.springframework.security.core.userdetails.User;

import com.example.demo.domain.entity.AppUser;

public interface AppUserDetailsService {
	public List<AppUser> findAllUsers();
	
	public User getCurrentUser();
	public AppUser findByUsername(String username);

	void updateAuthority();
}
