package com.example.demo.service.interfaces;

import java.util.List;
import java.util.Set;

import org.springframework.security.core.userdetails.User;

import com.example.demo.domain.entity.AppUser;

public interface FacadeService {
	public List<AppUser> findAllUsers();
	public User getCurrentUser();
	public void updateAuthority();
	
	
	public Set<String> getAccessibleUrls();
	public Set<String> getAccessibleUrls2();
	public Set<String> getAccessibleUrls3();
}
