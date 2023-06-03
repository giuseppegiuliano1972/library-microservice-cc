package com.la.auth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.la.auth.assembler.UserAssembler;
import com.la.auth.domain.User;
import com.la.auth.exception.AuthCustomException;
import com.la.auth.payload.request.UserDto;
import com.la.auth.repository.UserDao;

@Service
public class UserService {
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private UserAssembler userAssembler;
	
	public UserDto getUserByUserid(String userid, String password) {
		
		User user = userDao.findByUserid(userid);
		
		if (user.getId() == null)	{
			throw new AuthCustomException("Error: LOGIN FAILED", "MEMBER_NOT_ALLOWED");
		} else if (!password.equals(user.getPassword()))	{
			throw new AuthCustomException("Error: LOGIN FAILED", "MEMBER_NOT_ALLOWED");
		} 
			
		UserDto dto = userAssembler.DaoToDto(user);
		
		return dto;
		
	}

}
