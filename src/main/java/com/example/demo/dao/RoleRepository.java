package com.example.demo.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.entity.Role;
@Repository
public interface RoleRepository extends JpaRepository<Role, Long>{
	Role findById(int id);
	
	Role findByRole(String role);
}
