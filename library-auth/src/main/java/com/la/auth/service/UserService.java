package com.la.auth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.la.auth.assembler.UserAssembler;
import com.la.auth.config.ServiceAuthConfig;
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
	
	@Autowired 
	private ServiceAuthConfig serviceAuthConfig;
	
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
	
	public UserDto getUserByUserid(String userid, Boolean isNew) {
		
		User user = userDao.findByUserid(userid);
		
		if (user == null && isNew) {
			return null;
		}
		
		if (user.getId() == null && !isNew)	{
			throw new AuthCustomException("Error: LOGIN FAILED", "MEMBER_NOT_ALLOWED");
		} else if (user.getId() == null) {
			return new UserDto();
		}
		
		UserDto dto = userAssembler.DaoToDto(user);
		
		return dto;
		
	}
	
	public void saveUser(UserDto user) {
		
		User dao = userAssembler.DtoToDao(user);
		
		userDao.save(dao);
		
		
	}
	
	public String createToken() {
		String serviceBGreetingResponse = serviceAuthConfig.getServiceBAuthToken();

	    return serviceBGreetingResponse;
	}

}
