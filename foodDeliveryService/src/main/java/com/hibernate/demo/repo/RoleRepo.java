package com.hibernate.demo.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hibernate.demo.dto.Role;
import com.hibernate.demo.enums.ERole;

public interface RoleRepo extends JpaRepository<Role, Long> {
	Optional <Role> findByRoleName(ERole roleName);

}
