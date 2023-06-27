package com.la.auth.assembler;

import org.springframework.stereotype.Component;

import com.la.auth.domain.User;
import com.la.auth.payload.request.UserDto;

@Component
public class UserAssembler {
	
	public UserDto DaoToDto(User dao) {
		UserDto dto = new UserDto();
		
		dto.setId(dao.getId());
		dto.setUserid(dao.getUserid());
		dto.setPassword(dao.getPassword());
		return dto;

	}
	
	public User DtoToDao(UserDto dto) {
		User dao = new User();
		
		dao.setUserid(dto.getUserid());
		dao.setPassword(dto.getPassword());
		dao.setEmail(dto.getEmail());
		dao.setRoles(dto.getRole());
		return dao;

	}
	
	
}
