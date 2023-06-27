package com.la.auth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.la.auth.domain.ERole;
import com.la.auth.domain.Role;
import com.la.auth.repository.RoleDao;

@Service
public class RoleService {
	
	@Autowired
	private RoleDao roleDao;
	
	
	public Role getRoleByName(ERole roleName) {
		
		return roleDao.findByName(roleName).orElseThrow(() -> new RuntimeException("Error: Role is not found."));
		
	}
}
