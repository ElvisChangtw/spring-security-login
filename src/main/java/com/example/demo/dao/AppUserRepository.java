package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.domain.entity.AppUser;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Long>{

	AppUser findByUsername(String username);
	
}
