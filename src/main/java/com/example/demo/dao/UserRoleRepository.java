package com.example.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.entity.UserRole;
import com.example.demo.domain.entity.compositekey.UserRoleUPK;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, UserRoleUPK> {

	List<UserRole> findByUserRoleUPKUserId(int userId);
}
