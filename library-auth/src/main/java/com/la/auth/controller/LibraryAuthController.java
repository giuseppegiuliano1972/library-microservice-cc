package com.la.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.la.auth.payload.request.UserDto;
import com.la.auth.service.UserService;

import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("/auth")
@CrossOrigin
@Log4j2
public class LibraryAuthController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/login")
	public ResponseEntity<UserDto> login (@RequestBody UserDto user){
		
		return new ResponseEntity<>(
				userService.getUserByUserid(user.getUserid(), user.getPassword()),
                HttpStatus.OK
        );
	}

}
