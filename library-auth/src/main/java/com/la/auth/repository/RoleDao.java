package com.la.auth.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.la.auth.domain.ERole;
import com.la.auth.domain.Role;

@Repository
public interface RoleDao extends JpaRepository <Role,Long>{
	Optional<Role> findByName(ERole name);
}
