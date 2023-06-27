package com.la.auth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.la.auth.domain.User;
import com.la.auth.repository.UserDao;

import jakarta.transaction.Transactional;

@Service
public class UserDetailServiceImpl  implements UserDetailsService {
	@Autowired
	private UserDao userRepository;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUserid(username);

		return UserDetailsImpl.build(user);
	}

}