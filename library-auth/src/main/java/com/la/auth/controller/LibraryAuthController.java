package com.la.auth.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.la.auth.domain.ERole;
import com.la.auth.domain.Role;
import com.la.auth.jwt.JwtUtils;
import com.la.auth.payload.request.UserDto;
import com.la.auth.payload.response.ErrorResponse;
import com.la.auth.payload.response.JwtResponse;
import com.la.auth.service.RoleService;
import com.la.auth.service.UserDetailsImpl;
import com.la.auth.service.UserService;

import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("/auth")
@CrossOrigin
@Log4j2
public class LibraryAuthController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private PasswordEncoder encoder;
	
	@Autowired
	private JwtUtils jwtUtils;
	
	@PostMapping("/login")
	public ResponseEntity<?> login (@RequestBody UserDto user){
		
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(user.getUserid(), user.getPassword()));
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		//String jwt = jwtUtils.generateJwtToken(authentication);
		
		String jwt = userService.createToken();
		
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();		
		
		List<String> roles = userDetails.getAuthorities().stream()
				.map(item -> item.getAuthority())
				.collect(Collectors.toList());

		return ResponseEntity.ok(new JwtResponse(jwt, 
												 userDetails.getId(), 
												 userDetails.getUsername(), 
												 userDetails.getEmail(), 
												 roles));
		
//		return new ResponseEntity<>(
//				userService.getUserByUserid(user.getUserid(), user.getPassword()),
//                HttpStatus.OK
//        );
	}
	

	@PostMapping("/signup")
	public ResponseEntity<?> signup(@RequestBody UserDto user) {
		if (userService.getUserByUserid(user.getUserid(),Boolean.TRUE) != null) {
			return ResponseEntity
					.badRequest()
					.body(new ErrorResponse("Error: Username is already taken!", "99"));
		}
		
		user.setPassword(encoder.encode(user.getPassword()));
		
		Set<Role> roles = new HashSet<>();
		
		Role adminRole = roleService.getRoleByName(ERole.ROLE_ADMIN);
				
		roles.add(adminRole);
		user.setRole(roles);
		
		userService.saveUser(user);

		return ResponseEntity.ok(new ErrorResponse("User registered successfully!", null));
	}

}
